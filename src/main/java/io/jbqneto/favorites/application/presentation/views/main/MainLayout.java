package io.jbqneto.favorites.application.presentation.views.main;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import io.jbqneto.favorites.application.presentation.views.page.DashboardView;
import io.jbqneto.favorites.application.presentation.views.page.FavoritesView;

@PageTitle("Main")
public class MainLayout extends AppLayout {

    private TextField name;
    private Button sayHello;

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        var logo = new H1("Vaadin Favorites");
        logo.addClassNames("text-l", "m-m");

        var header = new HorizontalLayout(new DrawerToggle(), logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);
    }

    private void createDrawer() {
        addToDrawer(new VerticalLayout(
                createLink("DashBoard", DashboardView.class),
                createLink("My Favorites", FavoritesView.class)
        ));
    }

    private RouterLink createLink(String name, Class clazz) {
        var listView = new RouterLink(name, clazz);
        listView.setHighlightCondition(HighlightConditions.sameLocation());

        return listView;
    }

}
