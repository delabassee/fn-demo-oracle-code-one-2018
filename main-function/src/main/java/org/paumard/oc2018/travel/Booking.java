package org.paumard.oc2018.travel;

import com.fnproject.fn.api.FnFeature;
import com.fnproject.fn.api.Headers;
import com.fnproject.fn.api.flow.FlowFuture;
import com.fnproject.fn.api.flow.Flows;
import com.fnproject.fn.api.flow.HttpMethod;
import com.fnproject.fn.api.flow.HttpResponse;
import com.fnproject.fn.runtime.flow.FlowFeature;

import java.util.function.Function;

@FnFeature(FlowFeature.class)
public class Booking {

    private final String destinationRecommandationID = "01CT153WCRNG8G00GZJ000006B";

    public String book(int numberOfDestination) {

        FnFunction<Integer> httpResponseFlowFuture = FnFunction.of(destinationRecommandationID);

        String destinationsAsJson =
                httpResponseFlowFuture.apply(numberOfDestination)
                        .thenApply(HttpResponse::getBodyAsBytes)
                        .thenApply(String::new)
                        .get();

        return destinationsAsJson;
    }

    private interface FnFunction<T> extends Function<T, FlowFuture<HttpResponse>> {

        static <T> FnFunction<T> of(String destinationRecommandationID) {
            return input -> Flows.currentFlow().invokeFunction(
                    destinationRecommandationID, HttpMethod.POST, Headers.emptyHeaders(),
                    input);
        }
    }
}
