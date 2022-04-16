package io.jbqneto.favorites.application.domain.service;

import io.jbqneto.favorites.application.domain.model.business.Category;
import io.jbqneto.favorites.application.infrastructure.entity.CategoryEntity;
import io.jbqneto.favorites.application.infrastructure.repository.port.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends CRUDService<CategoryEntity, Category>{

    public CategoryService(CategoryRepository repository) {
        super(repository);
    }

    @Override
    public Category mapFromEntity(CategoryEntity entity) {
        var category = new Category(
                entity.getId(),
                entity.getName()
        );

        if (entity.getCategory() != null)
            category.setCategory(mapFromEntity(entity.getCategory()));

        category.setCountFavorites(entity.getFavoritesCount());

        return category;
    }

    @Override
    public CategoryEntity mapFromDomain(Category model) {
        var entity = new CategoryEntity(model.getId());
        entity.setName(model.getName());

        if (model.getCategory() != null)
            entity.setCategory(mapFromDomain(model.getCategory()));

        return entity;
    }
}
