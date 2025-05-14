package com.springcore.tasktwo.metricStorage;

import java.time.LocalDateTime;
import java.util.Objects;

public class MetricMethod {

    private final String nameClass;

    private final String nameMethod;

    private final LocalDateTime timeAndDateCalls;

    private final long executionTime;
    
    public MetricMethod(String nameClass, String getNameClass, LocalDateTime timeAndDateCalls, long executionTime) {
        this.nameClass = nameClass;
        this.nameMethod = getNameClass;
        this.timeAndDateCalls = timeAndDateCalls;
        this.executionTime = executionTime;
    }

    public String getNameClass() {
        return nameClass;
    }

    public String getNameMethod() {
        return nameMethod;
    }

    public LocalDateTime getTimeAndDateCalls() {
        return timeAndDateCalls;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MetricMethod that = (MetricMethod) o;
        return executionTime == that.executionTime && Objects.equals(nameClass, that.nameClass) && Objects.equals(nameMethod, that.nameMethod) && Objects.equals(timeAndDateCalls, that.timeAndDateCalls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameClass, nameMethod, timeAndDateCalls, executionTime);
    }

    @Override
    public String toString() {
        return "MetricMethod{" +
                "nameClass='" + nameClass + '\'' +
                ", nameMethod='" + nameMethod + '\'' +
                ", timeAndDateCalls=" + timeAndDateCalls +
                ", executionTime=" + executionTime +
                '}';
    }
}