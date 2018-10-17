package org.paumard.oc2018.travel;

import com.fnproject.fn.api.FnFeature;
import com.fnproject.fn.runtime.flow.FlowFeature;
import org.paumard.oc2018.travel.model.Destination;

import java.util.List;

@FnFeature(FlowFeature.class)
public class Booking {

    public List<Destination> book(int numberOfDestination) {

        Destination paris = Destination.of("Paris");
        Destination brussels = Destination.of("Brussels");

        List<Destination> destinations = List.of(paris, brussels);

        return destinations;
    }
}
