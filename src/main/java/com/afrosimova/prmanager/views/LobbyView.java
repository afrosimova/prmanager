package com.afrosimova.prmanager.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route(value = "")
@AnonymousAllowed
public class LobbyView extends VerticalLayout {
    public LobbyView() {
        setSizeFull();
        Component title = new H1("Performance Review Manager");
        title.addClassNames(LumoUtility.TextColor.PRIMARY,
                LumoUtility.TextAlignment.CENTER,
                LumoUtility.AlignSelf.CENTER,
                LumoUtility.Padding.LARGE);
        add(title);
        addClassNames(LumoUtility.JustifyContent.CENTER);
        Button employeeButton = new Button("Співробітник",
                e -> getUI().ifPresent((a -> a.navigate("employee_surveys"))));
        styleButton(employeeButton);
        Button managerButton = new Button("Менеджер",
                e -> getUI().ifPresent((a -> a.navigate("manager_surveys"))));
        styleButton(managerButton);
        Button adminButton = new Button("Адміністратор",
                e -> getUI().ifPresent((a -> a.navigate(""))));
        styleButton(adminButton);
        add(employeeButton, managerButton, adminButton);
    }

    private void styleButton(Button button) {
        button.addClassNames(LumoUtility.AlignSelf.CENTER,
                LumoUtility.Background.SUCCESS_50,
                LumoUtility.TextColor.SUCCESS_CONTRAST,
                LumoUtility.BoxShadow.XLARGE,
                LumoUtility.BorderRadius.LARGE);
        button.setWidth("160px");
    }
}
