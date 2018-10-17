package org.paumard.oc2018.travel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fnproject.fn.api.FnFeature;
import com.fnproject.fn.api.Headers;
import com.fnproject.fn.api.flow.FlowFuture;
import com.fnproject.fn.api.flow.Flows;
import com.fnproject.fn.api.flow.HttpMethod;
import com.fnproject.fn.api.flow.HttpResponse;
import com.fnproject.fn.runtime.flow.FlowFeature;
import org.paumard.oc2018.travel.model.Destination;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.stream.Collectors;

@FnFeature(FlowFeature.class)
public class Booking {

    private final String destinationRecommandationID = "01CT153WCRNG8G00GZJ000006B";
    private final String forecastID = "01CT17YHV5NG8G00GZJ000007V";

    public String book(int numberOfDestination) {

        FnFunction<Integer> httpResponseFlowFuture = FnFunction.of(destinationRecommandationID);

        String destinationsAsJson =
                httpResponseFlowFuture.apply(numberOfDestination)
                        .thenApply(HttpResponse::getBodyAsBytes)
                        .thenApply(String::new)
                        .get();

        List<Destination> destinations = unmarshal(destinationsAsJson);

        FnFunction<String> forecast = FnFunction.of(forecastID);

        Function<Destination, FlowFuture<Destination>> populateWithForecast =
                destination -> forecast.apply(destination.getName())
                        .thenApply(HttpResponse::getBodyAsBytes)
                        .thenApply(String::new)
                        .exceptionally(t -> "Forecast not found")
                        .thenApply(destination::withForecast);

        FlowFuture<Destination>[] destinationsWithForecastsFlowFutures =
                destinations.stream()
                        .map(populateWithForecast)
                        .toArray(FlowFuture[]::new);

        FlowFuture<Void> futureForecast =
                Flows.currentFlow().allOf(
                        destinationsWithForecastsFlowFutures);

        futureForecast.get();

        destinations = Arrays.stream(destinationsWithForecastsFlowFutures).map(FlowFuture::get).collect(Collectors.toList());
        destinationsAsJson = marshall(destinations);

        return destinationsAsJson;
    }

    public List<Destination> unmarshal(String json) {
        List<Destination> destinations = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CollectionType collectionOfDestination =
                    objectMapper.getTypeFactory()

                            .constructCollectionType(List.class, Destination.class);
            destinations = objectMapper.readValue(json, collectionOfDestination);
        } catch (IOException e) {
            throw new IllegalArgumentException("Exception during JSON unmarshalling", e);
        }
        return destinations;
    }

    public String marshall(List<Destination> destinations) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(bos, destinations);
        } catch (IOException e) {
            throw new IllegalArgumentException("Exception during JSON marshalling", e);
        }

        String json = new String(bos.toByteArray());
        return json;
    }

    private interface FnFunction<T> extends Function<T, FlowFuture<HttpResponse>> {

        static <T> FnFunction<T> of(String destinationRecommandationID) {
            return input -> Flows.currentFlow().invokeFunction(
                    destinationRecommandationID, HttpMethod.POST, Headers.emptyHeaders(),
                    input);
        }
    }
}
