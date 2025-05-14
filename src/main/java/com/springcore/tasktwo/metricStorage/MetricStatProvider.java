package com.springcore.tasktwo.metricStorage;

import java.time.LocalDateTime;
import java.util.List;

public interface MetricStatProvider{

    /**
     * Получить статистику по метрикам по всем методам за указанный период
     */
    List<MetricMethod> getTotalStatForPeriod(LocalDateTime from, LocalDateTime to);

    /**
     * Получить статистику по метрикам по указанному методу за указанный период
     */
    MetricMethod getTotalStatByMethodForPeriod(String method, LocalDateTime from, LocalDateTime to);
}