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


    public String recommand(String input) throws JsonProcessingException {

        int n = Integer.parseInt(input);
        n = Integer.min(destinations.size(), n);

        List<Destination> destinations = new ArrayList<>(this.destinations);
        Collections.shuffle(destinations);
        destinations = destinations.subList(0, n);

        ObjectMapper objectMapper = new ObjectMapper();
        String destinationsAsJson = objectMapper.writeValueAsString(destinations);
        return destinationsAsJson;
    }
}
