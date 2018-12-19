package com.forum.areas.category.models.view;

public class CategoryNamesViewModel {
    private String name;

    public CategoryNamesViewModel() {
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
