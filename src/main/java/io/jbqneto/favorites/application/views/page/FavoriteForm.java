package io.jbqneto.favorites.application.views.page;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import io.jbqneto.favorites.domain.model.business.Category;

import java.util.List;

public class FavoriteForm extends FormLayout {
    TextField title = new TextField("Title");
    TextField url = new TextField("Url");
    TextArea description = new TextArea("Description");
    ComboBox<Category> category = new ComboBox<>("Category");

    Button saveButton = new Button("Save");
    Button deleteButton = new Button("Delete");
    Button cancelButton = new Button("Cancel");

    public FavoriteForm(List<Category> categories) {
        addClassName("favorite-form");

        category.setItems(categories);
        category.setItemLabelGenerator(Category::getName);

        add(title, url, category, description, createButtonLayout());
    }

    private Component createButtonLayout() {
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        saveButton.addClickShortcut(Key.ENTER);
        cancelButton.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(saveButton, deleteButton, cancelButton);
    }
}
