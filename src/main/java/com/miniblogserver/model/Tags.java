package com.miniblogserver.model;

public class Tags {
    private Long id;
    private String name;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setTagName(String name) {
        this.name = name;
    }
}
