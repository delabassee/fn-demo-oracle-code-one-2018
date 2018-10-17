package org.paumard.oc2018.weather;

import com.fnproject.fn.api.FnFeature;
import com.fnproject.fn.runtime.flow.FlowFeature;

import java.util.Random;
import java.util.concurrent.TimeoutException;

@FnFeature(FlowFeature.class)
public class WeatherForecast {


    public String forecast(String destinationName) throws TimeoutException {

        Random random = new Random();
        int waitingTime = 100 + random.nextInt(100);
        if (waitingTime < 150) {
            waitFor(waitingTime);
        } else {
            throw new TimeoutException("No forecast for you!");
        }

        return "This is the weather for " + destinationName;
    }

    private void waitFor(int waitingTime) {
        try {
            Thread.sleep(waitingTime);
        } catch (InterruptedException e) {
        }
    }
}
