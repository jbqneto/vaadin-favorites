package io.jbqneto.favorites.infrastructure.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @ManyToOne(optional = true)
    @JoinColumn(name = "category_id", nullable = true)
    private CategoryEntity category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
