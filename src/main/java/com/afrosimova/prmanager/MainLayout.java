package com.afrosimova.prmanager;
import java.util.Optional;

//import com.afrosimova.prmanager.ExitView;
//import com.afrosimova.prmanager.views.SurveyView;
import com.afrosimova.prmanager.security.SecurityService;
import com.afrosimova.prmanager.views.SurveysView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
//import org.springframework.samples.petclinic.ui.view.ErrorView;
//import org.springframework.samples.petclinic.ui.view.WelcomeView;
//import org.springframework.samples.petclinic.ui.view.owner.OwnersFindView;
//import org.springframework.samples.petclinic.ui.view.vet.VetsView;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private final Tabs menu;
    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        menu = createMenu();
        addToNavbar(createHeaderContent());
    }

    private Component createHeaderContent() {

        String u = securityService.getAuthenticatedUser().getUsername();
        Button logout = new Button("Log out " + u, e -> securityService.logout()); // <2>

        var header = new HorizontalLayout(new DrawerToggle(), logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header);

        final HorizontalLayout layout = new HorizontalLayout();
        layout.getThemeList().add("dark");
        layout.setPadding(true);
        layout.setSpacing(false);
        layout.setId("header");
        layout.setWidthFull();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN);

        final Span brand = new Span();
        final Anchor brandLink = new Anchor("/", brand);
        brandLink.addClassName("navbar-brand");
        layout.add(brandLink);

        layout.add(menu);

        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        tabs.add(createMenuItems());
        return tabs;
    }

    private Tab[] createMenuItems() {
        return new Tab[] {
                new Tab(createRouterLink("Surveys", VaadinIcon.LIST, SurveysView.class)),
               // new Tab(createRouterLink("Survey", VaadinIcon.SEARCH, SurveyView.class)),
                //new Tab(createRouterLink("Result", VaadinIcon.LIST, ResultView.class)),
                //new Tab(createRouterLink("Error", VaadinIcon.WARNING, org.springframework.samples.petclinic.ui.view.ExitView.class))
        };
    }

    private RouterLink createRouterLink(String translationKey, VaadinIcon viewIcon,
                                        Class<? extends Component> navigationTarget) {
        final RouterLink routerLink =
                new RouterLink(getTranslation(translationKey), navigationTarget);
        routerLink.addComponentAsFirst(viewIcon.create());
        routerLink.addClassNames("flex", "gap-s", "uppercase");
        return routerLink;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        updateChrome();
    }

    private void updateChrome() {
        getTabWithCurrentRoute().ifPresent(menu::setSelectedTab);
    }

    private Optional<Tab> getTabWithCurrentRoute() {
        final String currentRoute = RouteConfiguration.forSessionScope()
                .getUrl(getContent().getClass());
        return menu.getChildren().filter(tab -> hasLink(tab, currentRoute))
                .findFirst().map(Tab.class::cast);
    }

    private boolean hasLink(Component tab, String currentRoute) {
        return tab.getChildren().filter(RouterLink.class::isInstance)
                .map(RouterLink.class::cast).map(RouterLink::getHref)
                .anyMatch(currentRoute::equals);
    }

}
