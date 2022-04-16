package io.jbqneto.favorites.application.domain.service;

import io.jbqneto.favorites.application.domain.model.business.Category;
import io.jbqneto.favorites.application.domain.model.business.Favorite;
import io.jbqneto.favorites.application.infrastructure.entity.CategoryEntity;
import io.jbqneto.favorites.application.infrastructure.entity.FavoriteEntity;
import io.jbqneto.favorites.application.infrastructure.repository.port.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService extends CRUDService<FavoriteEntity, Favorite> {

    public FavoriteService(FavoriteRepository repository) {
        super(repository);
    }

    public List<Favorite> search(String filter) {
        return this.getRepository().search(filter)
                .stream().map(this::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Favorite mapFromEntity(FavoriteEntity entity) {
        return new Favorite(
                entity.getId(),
                entity.getTitle(),
                entity.getUrl(),
                new Category(entity.getCategory().getId(), entity.getCategory().getName()),
                entity.getDescription()
        );
    }

    @Override
    public FavoriteEntity mapFromDomain(Favorite model) {
        var entity = new FavoriteEntity();
        entity.setId(model.getId());
        entity.setUrl(model.getUrl());
        entity.setTitle(model.getTitle());
        entity.setCategory(new CategoryEntity(model.getCategory().getId()));
        entity.setDescription(model.getDescription());

        return entity;
    }

    public FavoriteRepository getRepository() {
        return (FavoriteRepository) this.repository;
    }

    public long countFavorites() {
        return this.repository.count();
    }
}
