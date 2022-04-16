package io.jbqneto.favorites.application.presentation.views.page;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import io.jbqneto.favorites.application.domain.service.CategoryService;
import io.jbqneto.favorites.application.domain.service.FavoriteService;
import io.jbqneto.favorites.application.presentation.views.main.MainLayout;

import javax.annotation.security.PermitAll;

@PermitAll
@PageTitle("Vaadin Favorites | Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
public class DashboardView extends VerticalLayout {

    private final FavoriteService favoriteService;
    private final CategoryService categoryService;

    public DashboardView(FavoriteService favoriteService, CategoryService categoryService) {
        this.favoriteService = favoriteService;
        this.categoryService = categoryService;
        this.addClassName("dashboard-view");
        this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        this.add(this.getFavoritesStatus());
    }

    private Component getFavoritesStatus() {
        var span = new Span(this.favoriteService.countFavorites() + " favorites");
        span.addClassNames("text-xl", "mt-m");

        return span;
    }

    private Component getFavoritesChart() {
        return null;
    }
}
