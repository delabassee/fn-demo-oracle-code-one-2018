package org.paumard.oc2018.quotation;

import com.fnproject.fn.api.FnFeature;
import com.fnproject.fn.runtime.flow.FlowFeature;

import java.util.Random;

@FnFeature(FlowFeature.class)
public class Quotation {

    public int quote(String destinationName) {

        Random random = new Random();
        int waitingTime =  100 + random.nextInt(100);
        int price = 100 + random.nextInt(100);
        waitFor(waitingTime);
        return price;
    }

    private void waitFor(int waitingTime) {
        try {
            Thread.sleep(waitingTime);
        } catch (InterruptedException e) {
        }
    }
}
