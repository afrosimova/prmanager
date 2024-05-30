package com.afrosimova.prmanager.views.employee;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm login = new LoginForm();

    public LoginView(){
        addClassName("login-view");
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

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");

        add(new H1("Performance Review"), login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        // inform the user about an authentication error
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}
