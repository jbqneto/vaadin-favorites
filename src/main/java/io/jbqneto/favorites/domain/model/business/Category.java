package io.jbqneto.favorites.domain.model.business;

public class Category {
    private final long id;
    private final String name;
    private Category category;

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
}
