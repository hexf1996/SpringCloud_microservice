package com.hexf.es.bean;

import io.searchbox.annotations.JestId;

public class Book {

    @JestId
    private Integer id;

    private String name;

    private String type;

    public Book() {
    }

    public Book(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
