package com.eins.learn.patterns.lazyinit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LazyChart {
    private List<ChartValue> values = new ArrayList<>();
    private double startVal;
    private Function<Double, Double> func;
    private Function<Double, Double> stepFunc;
    private Double currentVal;

    public LazyChart(double startVal, Function<Double, Double> func, Function<Double, Double> stepFunc) {
        this.startVal = startVal;
        this.func = func;
        this.stepFunc = stepFunc;
    }

    public List<Double> getNextValues(int length) {
        currentVal = currentVal == null ? Double.valueOf(startVal) : stepFunc.apply(currentVal);
        Integer index = null;
        if (isPresent(currentVal)) {
            index = getIndexOf(currentVal);
        }
        List<ChartValue> result;
        if (index != null) {
            result = new ArrayList<>(values.subList(index, values.size()));
        } else {
            result = new ArrayList<>();
        }
        for (int i = 0; i < length; i++) {
            if (isNotPresent(currentVal)) {
                ChartValue chartValue = new ChartValue(currentVal, func.apply(currentVal));
                values.add(chartValue);
                result.add(chartValue);
            }
            currentVal = stepFunc.apply(currentVal);
        }
        return getAllY(result);
    }

    public List<Double> getPreviousValues(int length) {
        int size = values.size();
        if (length >= size) {
            currentVal = startVal;
            return new ArrayList<>(getAllY(values));
        }
        int from = size - length - 1;
        currentVal = values.get(from).getX();
        return new ArrayList<>(getSubY(values, from, size - 1));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class ChartValue {
        private double x;
        private double y;
    }

    private boolean isPresent(double x) {
        return values.stream().anyMatch(chartValue -> chartValue.getX() == x);
    }

    private boolean isNotPresent(double x) {
        return !isPresent(x);
    }

    private List<Double> getAllY(List<ChartValue> chartValues) {
        return chartValues.stream().map(ChartValue::getY).collect(Collectors.toList());
    }

    private List<Double> getSubY(List<ChartValue> chartValues, int from, int to) {
        return chartValues.subList(from, to).stream().map(ChartValue::getY).collect(Collectors.toList());
    }

    private int getIndexOf(double x) {
        ChartValue value = this.values.stream().filter(chartValue -> chartValue.getX() == x).findAny()
                .orElse(null);
        return value == null ? -1 : values.indexOf(value);
    }
}
