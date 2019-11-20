package com.hexf.es;


import com.hexf.es.bean.Book;
import com.hexf.es.bean.BookSecond;
import com.hexf.es.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ESApp.class)
public class ESTest {

    @Autowired
    private JestClient jestClient;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void addIndex() {
        Book book = new Book(1, "Java高级编程", "Java");
        Index index = new Index.Builder(book).index("test").type("book").build();
        try {
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchIndex() {
        String query = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"name\" : \"Java\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        Search search = new Search.Builder(query).addIndex("test").addType("book").build();
        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void indexBookRepository() {
        BookSecond book2 = new BookSecond();
        book2.setId(10);
        book2.setName("ttttttttttttttttttttttttttt");
        book2.setType("Python");

        BookSecond book3 = new BookSecond(41, "Python 規約", "Python");

        bookRepository.index(book3);
    }

    public void searchBookRepository() {
//        QueryBuilder builder =
//        bookRepository.search();
    }
}
