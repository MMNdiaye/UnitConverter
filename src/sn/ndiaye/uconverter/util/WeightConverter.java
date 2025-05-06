package sn.ndiaye.uconverter.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Set;

public class WeightConverter implements MeasureConverter{
    private final static double DEFAULT_SCALE = 1; // We consider the default scale to be 1 kilogram
    private final static double GRAM_SCALE =  DEFAULT_SCALE * 0.001;
    private final static double KILOGRAM_SCALE = DEFAULT_SCALE * 1;
    private final static double POUND_SCALE = DEFAULT_SCALE * 0.4535924;
    private final static HashMap<String, Double> scales;
    static {
        scales = new HashMap<>();
        scales.put("Gram", GRAM_SCALE);
        scales.put("Kilogram", KILOGRAM_SCALE);
        scales.put("Pound", POUND_SCALE);
    }

    public String convert(String unit1, String unit2, Double value) {
        BigDecimal scale1 = BigDecimal.valueOf(scales.get(unit1));
        BigDecimal scale2 = BigDecimal.valueOf(scales.get(unit2));
        BigDecimal unitRatio = scale1.divide(scale2);
        BigDecimal conversion = BigDecimal.valueOf(value).multiply(unitRatio);
        return String.valueOf(conversion);
    }


    public String getMeasure() {
        return "Weight";
    }


    public Set<String> getUnits() {
        return scales.keySet();
    }
}
