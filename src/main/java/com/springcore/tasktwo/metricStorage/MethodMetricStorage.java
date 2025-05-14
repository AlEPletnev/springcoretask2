package com.springcore.tasktwo.metricStorage;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class MethodMetricStorage implements MetricStatProvider{

    private List<MetricMethod> metricsMethodsList = new ArrayList<>();

    @Override
    public List<MetricMethod> getTotalStatForPeriod(LocalDateTime from, LocalDateTime to) {
        List<MetricMethod> resultList = new ArrayList<>();
        for(MetricMethod metricMethod : metricsMethodsList){
            if(metricMethod.getTimeAndDateCalls().isAfter(from) && metricMethod.getTimeAndDateCalls().isBefore(to)){
                resultList.add(metricMethod);
            }
        }
        return resultList;
    }

    @Override
    public MetricMethod getTotalStatByMethodForPeriod(String method, LocalDateTime from, LocalDateTime to) {
        for(MetricMethod metricMethod : metricsMethodsList){
            if(metricMethod.getNameMethod().equals(method) && metricMethod.getTimeAndDateCalls().isAfter(from) && metricMethod.getTimeAndDateCalls().isBefore(to)){
                return metricMethod;
            }
        }
        return null;
    }

    public void push(MetricMethod metricMethod) {
        if(metricMethod != null){
            metricsMethodsList.add(metricMethod);
        }
    }

    public void deleteAll(){
        metricsMethodsList.clear();
    }

    public List<MetricMethod> getList(){
        return new ArrayList<>(metricsMethodsList);
    }
}
