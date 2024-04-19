package com.afrosimova.prmanager.views;

import com.afrosimova.prmanager.services.UserService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Route("/hello")
@RolesAllowed("ADMIN")
class MainView extends VerticalLayout {
    private final UserService userService;

    MainView(
            UserService userService
    ) {
        add(new H1("Hello, world!"));
        this.userService = userService;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        User user = User.builder()
//                .loginUser("ed")
//                .password("ede")
//                .isAdmin(true)
//                .build();
//        userService.save(user);
    }
}