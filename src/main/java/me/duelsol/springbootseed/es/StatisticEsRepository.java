package me.duelsol.springbootseed.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 冯奕骅
 */
public interface StatisticEsRepository extends ElasticsearchRepository<Statistic, Long> {
}
