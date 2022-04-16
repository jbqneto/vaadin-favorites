package io.jbqneto.favorites.application.presentation.views.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;
import io.jbqneto.favorites.application.domain.model.business.Category;
import io.jbqneto.favorites.application.domain.model.business.Favorite;
import io.jbqneto.favorites.application.domain.service.FavoriteService;
import io.jbqneto.favorites.application.presentation.views.event.FavoriteFormEvent;

import javax.validation.constraints.NotNull;
import java.util.List;

public class FavoriteForm extends FormLayout {
    TextField title = new TextField("Title");
    TextField url = new TextField("Url");
    TextArea description = new TextArea("Description");
    ComboBox<Category> category = new ComboBox<>("Category");

    Button saveButton = new Button("Save");
    Button deleteButton = new Button("Delete");
    Button cancelButton = new Button("Cancel");

    private Favorite favorite;

    private final FavoriteService service;

    public FavoriteForm(FavoriteService favoriteService, List<Category> categories) {
        this.service = favoriteService;

        addClassName("favorite-form");

        category.setItems(categories);
        category.setItemLabelGenerator(Category::getName);

        add(title, url, category, description, createButtonLayout());
    }

    private Component createButtonLayout() {
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.addClickShortcut(Key.ENTER);
        saveButton.addClickListener(event -> {
            if (this.favorite != null) {
                this.edit();
            } else {
                this.create();
            }
        });

        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteButton.addClickListener(event -> deleteFavorite());

        cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancelButton.addClickShortcut(Key.ESCAPE);
        cancelButton.addClickListener(event -> fireEvent(new FavoriteFormEvent.CloseEvent(this, this.favorite)));

        return new HorizontalLayout(saveButton, deleteButton, cancelButton);
    }

    private void deleteFavorite() {
        this.service.delete(this.favorite);
        this.fireEvent(new FavoriteFormEvent.DeleteEvent(this, this.favorite));
    }

    public void setFavorite(@NotNull Favorite favorite) {
        this.favorite = favorite;
        this.title.setValue(favorite.getTitle());
        this.url.setValue(favorite.getUrl());
        this.description.setValue(favorite.getDescription());
        this.category.setValue(favorite.getCategory());
    }

    private void create() {
        var favorite = new Favorite(
                0,
                this.title.getValue(),
                this.url.getValue(),
                this.category.getValue(),
                this.description.getValue()
        );

        this.validateAndSave(favorite);
    }

    private void edit() {
        var favorite = new Favorite(
                this.favorite.getId(),
                this.title.getValue(),
                this.url.getValue(),
                this.category.getValue(),
                this.description.getValue()
        );

        this.validateAndSave(favorite);
    }

    public void clearForm() {
        this.title.setValue("");
        this.url.setValue("");
        this.description.setValue("");
        this.category.setValue(null);
    }

    private void validateAndSave(Favorite favorite) {
        try {
            this.favorite = this.service.create(favorite);

            fireEvent(new FavoriteFormEvent.SaveEvent(this, this.favorite));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {

        return getEventBus().addListener(eventType, listener);
    }

}