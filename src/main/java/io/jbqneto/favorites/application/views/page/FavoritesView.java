package io.jbqneto.favorites.application.views.page;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import io.jbqneto.favorites.domain.model.business.Favorite;

import java.util.Collections;

@PageTitle("Favorites | Vaadin CRM")
@Route(value = "")
public class FavoritesView extends VerticalLayout {
    private Grid<Favorite> grid = new Grid<>(Favorite.class);
    private TextField filterText = new TextField();
    private FormLayout form;

    public FavoritesView() {
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(getToolbar(), getContent());

    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();

        return content;
    }

    private void configureForm() {
        form = new FavoriteForm(Collections.emptyList());
        form.setWidth("25em");
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        var button = new Button("Add Favorite");

        var toolbar = new HorizontalLayout(filterText, button);
        toolbar.addClassName("toolbar");

        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("favorites-grid");
        grid.setSizeFull();
        grid.setColumns("id", "title", "url");
        grid.addColumn(favorite -> favorite.getCategory().getName());
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
}
