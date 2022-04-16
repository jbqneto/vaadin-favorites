package io.jbqneto.favorites.application.presentation.views.page;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import io.jbqneto.favorites.application.domain.model.business.Favorite;
import io.jbqneto.favorites.application.domain.service.CategoryService;
import io.jbqneto.favorites.application.domain.service.FavoriteService;
import io.jbqneto.favorites.application.presentation.views.component.FavoriteForm;
import io.jbqneto.favorites.application.presentation.views.event.FavoriteFormEvent;
import io.jbqneto.favorites.application.presentation.views.main.MainLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;

@PermitAll
@PageTitle("Favorites | Vaadin CRM")
@Route(value = "", layout = MainLayout.class)
public class FavoritesView extends VerticalLayout {
    private Grid<Favorite> grid = new Grid<>(Favorite.class);
    private TextField filterText = new TextField();
    private FavoriteForm form;

    private final FavoriteService favoriteService;
    private final CategoryService categoryService;

    public static final Logger LOGGER = LoggerFactory.getLogger(FavoritesView.class);

    public FavoritesView(FavoriteService favoriteService, CategoryService categoryService) {
        this.favoriteService = favoriteService;
        this.categoryService = categoryService;

        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        
        this.closeEditor();
    }

    private void closeEditor() {
        this.form.clearForm();
        this.form.setVisible(false);
        this.removeClassName("editing");
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();

        this.updateFavorites();

        return content;
    }

    private void configureForm() {
        this.form = new FavoriteForm(this.favoriteService, this.categoryService.list());
        this.form.setWidth("25em");

        this.form.addListener(FavoriteFormEvent.SaveEvent.class, this::saveFavorite);
        this.form.addListener(FavoriteFormEvent.DeleteEvent.class, this::deleteFavorite);
        this.form.addListener(FavoriteFormEvent.CloseEvent.class, event -> this.closeEditor());
    }

    private void deleteFavorite(FavoriteFormEvent.DeleteEvent event) {
        this.updateFavorites();
        this.closeEditor();
    }

    private void saveFavorite(FavoriteFormEvent.SaveEvent event) {
        this.updateFavorites();
        this.closeEditor();
    }

    private void updateFavorites() {
        this.updateFavorites(null);
    }

    private void updateFavorites(String search) {
        if (search != null && !search.isEmpty()) {
            grid.setItems(this.favoriteService.search(search));
        } else {
            grid.setItems(this.favoriteService.list());
        }
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(event -> updateFavorites(event.getValue()));

        var addButton = new Button("Add Favorite");
        addButton.addClickListener(event -> this.addFavorite());

        var toolbar = new HorizontalLayout(filterText, addButton);
        toolbar.addClassName("toolbar");

        return toolbar;
    }

    private void addFavorite() {
        this.grid.asSingleSelect().clear();
        this.showForm();
    }

    private void configureGrid() {
        grid.addClassName("favorites-grid");
        grid.setSizeFull();
        grid.setColumns("id", "title", "url");
        grid.addColumn(favorite -> favorite.getCategory().getName());
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event -> {
            LOGGER.info("GRID SingleSelect Event {}", event);

            if (event.getValue() != null)
                this.editFavorite(event.getValue());
        });
    }

    private void editFavorite(Favorite value) {
        this.showForm();
        this.form.setFavorite(value);
    }

    private void showForm() {
        this.form.clearForm();
        this.form.setVisible(true);
        this.addClassName("editing");
    }
}
