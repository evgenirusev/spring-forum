package com.forum.cache;

import com.forum.areas.category.models.view.CategoryNamesViewModel;

import java.util.ArrayList;
import java.util.List;

public class DataCacheSingleton {

    private static final DataCacheSingleton instance = new DataCacheSingleton();

    private List<CategoryNamesViewModel> categories = new ArrayList<>();

    private DataCacheSingleton(){}

    public static DataCacheSingleton getInstance(){
        return instance;
    }

    public List<CategoryNamesViewModel> getCategories() {
        return this.categories;
    }

    public void addCategories(List<CategoryNamesViewModel> categories) {
        this.categories = categories;
    }
}
