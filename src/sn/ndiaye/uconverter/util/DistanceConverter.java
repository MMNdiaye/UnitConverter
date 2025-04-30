package sn.ndiaye.uconverter.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;

public class DistanceConverter implements MeasureConverter{
    private final static double DEFAULT_SCALE = 1; // We consider the default scale to be 1km
    private final static double KILOMETER_SCALE = 1 / DEFAULT_SCALE;
    private final static double METER_SCALE = 1000 / DEFAULT_SCALE;
    private final static HashMap<String, Double> scales;
    static {
        scales = new HashMap<>();
        scales.put("Kilometer", KILOMETER_SCALE);
        scales.put("Meter", METER_SCALE);
    }

    public String convert(String unit1, String unit2, Double value) {
        BigDecimal scale1 = BigDecimal.valueOf(scales.get(unit1));
        BigDecimal scale2 = BigDecimal.valueOf(scales.get(unit2));
        BigDecimal unitRatio = scale2.divide(scale1, MathContext.DECIMAL32);
        BigDecimal conversion = BigDecimal.valueOf(value).multiply(unitRatio);
        return String.valueOf(conversion);
    }

    public static void main(String[] args) {
        System.out.println(new DistanceConverter().convert("Meter", "Kilometer", 5.0));
    }
}
