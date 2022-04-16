package io.jbqneto.favorites.application.domain.model.business;

import javax.validation.constraints.NotNull;

public class Category implements IDModel {
    private final long id;

    @NotNull
    private final String name;

    private Category category;

    private int countFavorites;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getCountFavorites() {
        return countFavorites;
    }

    public void setCountFavorites(int countFavorites) {
        this.countFavorites = countFavorites;
    }
}
