package sn.ndiaye.uconverter.logic;

import java.util.HashMap;
import java.util.Set;

import sn.ndiaye.uconverter.util.DistanceConverter;
import sn.ndiaye.uconverter.util.MeasureConverter;
import sn.ndiaye.uconverter.views.ConverterGUI;

public class Manager {
    private HashMap<String, MeasureConverter> converters;
    private MeasureConverter converter;
    private ConverterGUI gUI;

    public Manager() {
        converters = new HashMap<>();
        converters.put("Distance", new DistanceConverter());
        converter = converters.get("Distance");
        this.gUI = new ConverterGUI(this);
    }

    public String process(String measure, String unit1, String unit2, String input) {
        return converter.convert(unit1, unit2, Double.valueOf(input));
    }

    public Set<String> getMeasures() {
        return converters.keySet();
    }

    public Set<String> getConverterUnits() {
        return converters.get(converter.getMeasure()).getUnits();
    }

    public void setConverter(String measure) {
        if (converters.containsKey(measure))
            converter = converters.get(measure);
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
    }
}
