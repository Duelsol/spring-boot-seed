package me.duelsol.springbootseed.service.statistic;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 冯奕骅
 */
public interface IStatisticService {

    List<Double> findTopScores(String name, LocalDateTime start, LocalDateTime end);

    void saveStatistic(int score);

}
