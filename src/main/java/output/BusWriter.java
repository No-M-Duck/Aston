package output;

import models.Bus;

import java.util.ArrayList;
import java.util.List;

public class BusWriter extends CollectionWriter<Bus> {
    public void toFile (List<Bus> buses) {
        ArrayList<String> busesOutput = new ArrayList<>();
        for (Bus bus : buses) {
            String busString = getString(bus);
        }
        writeCollection("buses", busesOutput);
    }

    public void toFile (Bus bus) {
        String busString = getString(bus);
        writeElement("buses_found", busString);
    }

    private String getString(Bus bus) {
        return bus.getNumber()+delimiter+bus.getModel()+delimiter+bus.getMileage();
    }
}
