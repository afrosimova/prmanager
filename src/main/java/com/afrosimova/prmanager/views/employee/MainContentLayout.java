package com.afrosimova.prmanager.views.employee;

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

import java.util.Objects;

@ParentLayout(MainLayout.class)
@Route
public class MainContentLayout extends VerticalLayout implements RouterLayout {

    private final Div content = new Div();

    public MainContentLayout() {
        setSizeFull();
        this.getThemeList().remove("margin");
        this.getThemeList().remove("padding");
        setMargin(false);
        setSpacing(false);
        setJustifyContentMode(JustifyContentMode.BETWEEN);
        content.setSizeFull();
        add(content);
    }

    @Override
    public void showRouterLayoutContent(HasElement hasElement) {
        Objects.requireNonNull(hasElement);
        Objects.requireNonNull(hasElement.getElement());
        content.removeAll();
        content.getElement().appendChild(hasElement.getElement());
    }
}
