package com.forum.dtos.category;

public class CreateCategoryDto {
    private String name;

    private String description;

    public CreateCategoryDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}