package com.afrosimova.prmanager.views.admin;

import com.afrosimova.prmanager.security.SecurityService;
import com.afrosimova.prmanager.views.admin.AdminSurveysView;
import com.afrosimova.prmanager.views.admin.UsersView;
import com.afrosimova.prmanager.views.employee.LobbyView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class AdminLayout extends AppLayout {
    private final SecurityService securityService;

    public AdminLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private RouterLink createRouterLink(String name, VaadinIcon viewIcon,
                                        Class<? extends Component> navigationTarget) {
        final RouterLink routerLink = new RouterLink(name, navigationTarget);
        routerLink.addComponentAsFirst(viewIcon.create());
        routerLink.addClassNames("flex", "gap-s", "uppercase");
        return routerLink;
    }

    private void createHeader() {
        H1 logo = new H1("Performance Review (Адміністратор)");
        logo.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM);

        String u = securityService.getAuthenticatedUser().getUsername();
        Button logout = new Button("Log out " + u, e -> securityService.logout()); // <2>

        var header = new HorizontalLayout(
                new Tab(createRouterLink("", VaadinIcon.HOME, LobbyView.class)),
                new DrawerToggle(), logo, logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo); // <4>
        header.setWidthFull();
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);
        header.getThemeList().add("dark");
        addToNavbar(header);

    }

    private void createDrawer() {
        addToDrawer(new VerticalLayout(
                new RouterLink("Користувачі", UsersView.class),
                //new RouterLink("Співробітники", UsersView.class),
                //new RouterLink("Посади", UsersView.class),
                new RouterLink("Оцінювання", AdminSurveysView.class),
                new RouterLink("Питання", AdminQuestionsView.class)
        ));
    }
}
