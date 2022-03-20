package io.jbqneto.favorites.infrastructure.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class FavoriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String title;

    @NotNull
    private String url;

    @ManyToOne(optional = true)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Column(name = "description")
    private String description;

}
