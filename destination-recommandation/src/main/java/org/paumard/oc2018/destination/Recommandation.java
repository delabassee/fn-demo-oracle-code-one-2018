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

package org.paumard.oc2018.destination;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fnproject.fn.api.FnFeature;
import com.fnproject.fn.runtime.flow.FlowFeature;
import org.paumard.oc2018.destination.model.Destination;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@FnFeature(FlowFeature.class)
public class Recommandation {

    List<Destination> destinations =
            List.of(
                    Destination.of("Paris"),
                    Destination.of("Brussels"),
                    Destination.of("Antwerp"),
                    Destination.of("Amsterdam"),
                    Destination.of("Venice"),
                    Destination.of("Roma"),
                    Destination.of("London"),
                    Destination.of("Kiev"),
                    Destination.of("San Francisco"),
                    Destination.of("Los Angeles"),
                    Destination.of("Chicago")
            );


    public String recommand(int input) throws JsonProcessingException {

        int n = Integer.min(destinations.size(), input);

        List<Destination> destinations = new ArrayList<>(this.destinations);
        Collections.shuffle(destinations);
        destinations = destinations.subList(0, n);

        ObjectMapper objectMapper = new ObjectMapper();
        String destinationsAsJson = objectMapper.writeValueAsString(destinations);
        return destinationsAsJson;
    }
}
