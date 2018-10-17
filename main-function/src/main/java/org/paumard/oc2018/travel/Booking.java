package org.paumard.oc2018.travel;

import com.fnproject.fn.api.FnFeature;
import com.fnproject.fn.api.Headers;
import com.fnproject.fn.api.flow.FlowFuture;
import com.fnproject.fn.api.flow.Flows;
import com.fnproject.fn.api.flow.HttpMethod;
import com.fnproject.fn.api.flow.HttpResponse;
import com.fnproject.fn.runtime.flow.FlowFeature;

@FnFeature(FlowFeature.class)
public class Booking {

    private final String destinationRecommandationID = "01CT153WCRNG8G00GZJ000006B";

    public String book(int numberOfDestination) {

        FlowFuture<HttpResponse> httpResponseFlowFuture =
                Flows.currentFlow().invokeFunction(
                        destinationRecommandationID, HttpMethod.POST, Headers.emptyHeaders(),
                        numberOfDestination);

        String destinationsAsJson =
                httpResponseFlowFuture
                        .thenApply(HttpResponse::getBodyAsBytes)
                        .thenApply(String::new)
                        .get();

        return destinationsAsJson;
    }
}
