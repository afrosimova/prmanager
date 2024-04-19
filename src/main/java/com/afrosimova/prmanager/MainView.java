package com.afrosimova.prmanager;

import com.afrosimova.prmanager.Entity.User;
import com.afrosimova.prmanager.Services.UserService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/hello") // map view to the root
class MainView extends VerticalLayout {
    private final UserService userService;

    MainView(
            UserService userService
    ) {
        add(new H1("Hello, world!"));
        this.userService = userService;

        User user = User.builder()
                .loginUser("ed")
                .password("ede")
                .isAdmin(true)
                .build();
        userService.save(user);
    }
}