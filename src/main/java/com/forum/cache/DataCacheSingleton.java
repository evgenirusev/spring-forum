package com.forum.cache;

import com.forum.dtos.category.CategoryNamesDto;

import java.util.ArrayList;
import java.util.List;

public class DataCacheSingleton {

    private static final DataCacheSingleton instance = new DataCacheSingleton();

    private List<CategoryNamesDto> categories = new ArrayList<>();

    private DataCacheSingleton(){}

    public static DataCacheSingleton getInstance(){
        return instance;
    }

    public List<CategoryNamesDto> getCategories() {
        return this.categories;
    }

    public void addCategory(CategoryNamesDto categoryNamesDto) {
        this.categories.add(categoryNamesDto);
    }

    public void addCategories(List<CategoryNamesDto> categories) {
        this.categories = categories;
    }
}
