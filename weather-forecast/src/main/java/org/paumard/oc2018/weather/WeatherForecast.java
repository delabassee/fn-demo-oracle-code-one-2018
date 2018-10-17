package org.paumard.oc2018.weather;

import com.fnproject.fn.api.FnFeature;
import com.fnproject.fn.runtime.flow.FlowFeature;

import java.util.Random;

@FnFeature(FlowFeature.class)
public class WeatherForecast {


    public String forecast(String destinationName) {

        Random random = new Random();
        int waitingTime = 100 + random.nextInt(100);
        waitFor(waitingTime);

        return "This is the weather for " + destinationName;
    }

    private void waitFor(int waitingTime) {
        try {
            Thread.sleep(waitingTime);
        } catch (InterruptedException e) {
        }
    }
}
