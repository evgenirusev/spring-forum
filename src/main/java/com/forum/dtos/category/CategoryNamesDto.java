package com.forum.dtos.category;

public class CategoryNamesDto {
    private String name;

    public CategoryNamesDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
