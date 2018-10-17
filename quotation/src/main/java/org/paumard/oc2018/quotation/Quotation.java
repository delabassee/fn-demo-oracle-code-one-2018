package org.paumard.oc2018.quotation;

import com.fnproject.fn.api.FnFeature;
import com.fnproject.fn.runtime.flow.FlowFeature;

import java.util.Random;
import java.util.concurrent.TimeoutException;

@FnFeature(FlowFeature.class)
public class Quotation {

    public int quote(String destinationName) throws TimeoutException {

        Random random = new Random();
        int waitingTime =  100 + random.nextInt(100);
        int price = 100 + random.nextInt(100);
        if (waitingTime < 150) {
            waitFor(waitingTime);
        } else {
            throw new TimeoutException("No quotation for you!");
        }
        return price;
    }

    private void waitFor(int waitingTime) {
        try {
            Thread.sleep(waitingTime);
        } catch (InterruptedException e) {
        }
    }
}
