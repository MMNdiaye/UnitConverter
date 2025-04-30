package sn.ndiaye.uconverter.logic;

import java.util.HashMap;

import sn.ndiaye.uconverter.util.DistanceConverter;
import sn.ndiaye.uconverter.util.MeasureConverter;

public class Manager {
    private HashMap<String, MeasureConverter> converters;
    private MeasureConverter converter;

    public Manager() {
        converters = new HashMap<>();
        converters.put("Distance", new DistanceConverter());
        converter = converters.get("Distance");
    }

    public String process(String measure, String unit1, String unit2, String input) {
        if (!converter.getMeasure().equals(measure))
            converter = converters.get(measure);
        return converter.convert(unit1, unit2, Double.valueOf(input));
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        String output = manager.process("Distance",
                "Kilometer", "Meter", "12.6");
        System.out.println(output);
    }
}
