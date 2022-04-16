package io.jbqneto.favorites.application.domain.model.business;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Favorite implements IDModel {
    private final long id;

    @NotBlank
    @NotNull
    private final String title;

    @NotBlank
    private final String url;

    @NotNull
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

    public Favorite(long id, String title, String url, Category category, String description) {
        this(id, title, url);
        this.category = category;
        this.description = description;
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
