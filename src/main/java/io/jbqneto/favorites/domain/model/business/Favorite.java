package io.jbqneto.favorites.domain.model.business;

import java.util.Objects;

public class Favorite {
    private final long id;
    private final String title;
    private final String url;
    private Category category;
    private String description;

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Category getCategory() {
        return category;
    }

    public Favorite(long id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return id == favorite.id && url.equals(favorite.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url);
    }

}
