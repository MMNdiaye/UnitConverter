package sn.ndiaye.uconverter.util;

import java.util.Set;

public interface MeasureConverter {
    String convert(String unit1, String unit2, Double value);
    String getMeasure();
    Set<String> getUnits();
}
