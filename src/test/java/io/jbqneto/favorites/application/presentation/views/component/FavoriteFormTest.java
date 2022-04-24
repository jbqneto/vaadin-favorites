package io.jbqneto.favorites.application.presentation.views.component;

import io.jbqneto.favorites.application.domain.model.business.Category;
import io.jbqneto.favorites.application.domain.model.business.Favorite;
import io.jbqneto.favorites.application.domain.service.FavoriteService;
import io.jbqneto.favorites.application.infrastructure.entity.CategoryEntity;
import io.jbqneto.favorites.application.infrastructure.entity.FavoriteEntity;
import io.jbqneto.favorites.application.infrastructure.repository.port.FavoriteRepository;
import io.jbqneto.favorites.application.presentation.views.event.FavoriteFormEvent;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@ExtendWith(MockitoExtension.class)
class FavoriteFormTest {

    private Favorite favorite;
    private Category category;
    private FavoriteService service;

    private List<Category> categories;

    private FavoriteRepository repository;

    @BeforeEach
    public void setup() {
        repository = Mockito.mock(FavoriteRepository.class);
        service = new FavoriteService(repository);

        category = new Category(1, "Category 1");
        categories = List.of(
                category,
                new Category(2, "Category 2")
        );

        favorite = new Favorite(1, "A favorite", "http://favorite.com", category, "Nothing");
    }

    @org.junit.jupiter.api.Test
    public void formFieldPopulated() {
        FavoriteForm form = new FavoriteForm(service, categories);
        form.setFavorite(favorite);

        Assert.assertEquals(favorite.getTitle(), form.title.getValue());
        Assert.assertEquals(favorite.getUrl(), form.url.getValue());
        Assert.assertEquals(category, form.category.getValue());

    }

    @org.junit.jupiter.api.Test
    public void saveEventMustHasCorrectValues() {
        //arrange
        var categoryEntity = new CategoryEntity(category.getId());
        categoryEntity.setName(category.getName());

        var entity = new FavoriteEntity();
        entity.setUrl("http://testing.com");
        entity.setTitle("Testing");
        entity.setCategory(categoryEntity);

        FavoriteForm form = new FavoriteForm(service, categories);
        form.title.setValue("Testing");
        form.url.setValue("http://testing.com");
        form.category.setValue(category);

        AtomicReference<Favorite> saved = new AtomicReference(null);
        form.addListener(FavoriteFormEvent.SaveEvent.class, (event) -> saved.set(event.getFavorite()));

        Mockito.when(repository.save(Mockito.any()))
                .thenReturn(entity);

        //act
        form.saveButton.click();

        //assert
        Assert.assertEquals("Testing", saved.get().getTitle());
        Assert.assertEquals("http://testing.com", saved.get().getUrl());
        Assert.assertEquals(category, saved.get().getCategory());

    }
}