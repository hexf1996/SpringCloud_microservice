package com.hexf.es.repository;

import com.hexf.es.bean.BookSecond;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface BookRepository extends ElasticsearchRepository<BookSecond, Integer> {

}
