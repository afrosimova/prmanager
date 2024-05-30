package com.afrosimova.prmanager.views.employee;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route(value = "")
@AnonymousAllowed
public class LobbyView extends VerticalLayout {
    public LobbyView() {
        setClassName("lobby-view");
        setSizeFull();
        getElement().getStyle().set("background-image", "url('https://i.pinimg.com/originals/bc/fe/36/bcfe3678767235a08366a55d6ef7ac64.jpg')");

        //getElement().getStyle().set("height", "50%");
        //getElement().getStyle().set("width", "50%");
        getElement().getStyle().set("background-size", "cover");
        getElement().getStyle().set("background-position", "center");
        getElement().getStyle().set("background-repeat", "no-repeat");
        getElement().getStyle().set("display", "flex");
        getElement().getStyle().set("align-items", "center");
        getElement().getStyle().set("justify-content", "center");


//        getStyle().set("background-image", "url('img/test.png')");
//        getStyle().set("background-size", "cover");
//        getElement().getStyle().set("background-image", "url('img/test.png')");
//        getElement().getStyle().set("background-size", "cover");

        Component title = new H1("Performance Review");
        title.addClassNames(LumoUtility.TextColor.PRIMARY,
                LumoUtility.TextAlignment.CENTER,
                LumoUtility.AlignSelf.CENTER,
                LumoUtility.Padding.LARGE);
        add(title);
        addClassNames(LumoUtility.JustifyContent.CENTER);
        Button employeeButton = new Button("Співробітник",
                //employeeButton.getStyle().set("background-color", "blue"),
        e -> getUI().ifPresent((a -> a.navigate("employee_surveys"))));
        styleButton(employeeButton);
        Button managerButton = new Button("Керівник",
                e -> getUI().ifPresent((a -> a.navigate("manager_surveys"))));
        styleButton(managerButton);
        Button adminButton = new Button("Адміністратор",
                e -> getUI().ifPresent((a -> a.navigate("admin/users"))));
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

