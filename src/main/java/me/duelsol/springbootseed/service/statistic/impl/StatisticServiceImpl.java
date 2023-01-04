package me.duelsol.springbootseed.service.statistic.impl;

import me.duelsol.springbootseed.es.Statistic;
import me.duelsol.springbootseed.es.StatisticEsRepository;
import me.duelsol.springbootseed.service.statistic.IStatisticService;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHitsIterator;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 冯奕骅
 */
@Service
public class StatisticServiceImpl implements IStatisticService {

    @Autowired
    private StatisticEsRepository statisticEsRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public List<Double> findTopScores(String name, LocalDateTime start, LocalDateTime end) {
        List<Double> result = new ArrayList<>();
        if (start.isAfter(end)) {
            return result;
        }

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withIndicesOptions(IndicesOptions.fromMap(Collections.singletonMap("ignore_unavailable", true), IndicesOptions.strictExpandOpenAndForbidClosed()));
        builder.withPageable(PageRequest.of(0, 1));

        BoolQueryBuilder query = QueryBuilders.boolQuery();
        query.must(QueryBuilders.termQuery("name", name));
        query.must(QueryBuilders.rangeQuery("time").from(start).to(end).format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").timeZone("Asia/Shanghai"));
        builder.withQuery(query);

        TermsAggregationBuilder aggregation = AggregationBuilders.terms("top_scores")
                .field("name.keyword")
                .subAggregation(AggregationBuilders.avg("avg_score").field("score"))
                .order(BucketOrder.aggregation("avg_score", false))
                .size(10);
        builder.addAggregation(aggregation);

        SearchHitsIterator<Statistic> iterator = elasticsearchRestTemplate.searchForStream(builder.build(), Statistic.class, IndexCoordinates.of("statistic-*"));
        Aggregations aggregations = iterator.getAggregations();
        if (aggregations == null) {
            return result;
        }
        Terms terms = aggregations.get("top_scores");
        terms.getBuckets().forEach(bucket -> {
            ParsedAvg avg = bucket.getAggregations().get("avg_score");
            result.add(avg.getValue());
        });
        Collections.reverse(result);
        return result;
    }

    @Override
    public void saveStatistic(int score) {
        Statistic statistic = new Statistic();
        statistic.setName("demo");
        statistic.setScore(score);
        statistic.setTime(LocalDateTime.now());
        statisticEsRepository.save(statistic);
    }

}
