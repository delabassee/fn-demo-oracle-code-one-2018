/*
 * Copyright (C) 2018 José Paumard
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
