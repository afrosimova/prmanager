package com.afrosimova.prmanager.views.admin;

import com.afrosimova.prmanager.entities.User;
import com.afrosimova.prmanager.services.UserService;
import com.afrosimova.prmanager.views.admin.forms.UserForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "admin/users", layout = AdminLayout.class)
@RolesAllowed("ADMIN")
public class UsersView extends VerticalLayout {
    private final UserService userService;
    Grid<User> grid = new Grid<>(User.class);
    private UserForm form;

    public UsersView(UserService userService) {
        this.userService = userService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getContent(), getToolbar());
        updateList();
        closeEditor();
    }

    private void configureForm() {
        form = new UserForm();
        form.setWidth("25em");
        form.addSaveListener(this::saveUser); // <1>
        form.addDeleteListener(this::deleteUser); // <2>
        form.addCloseListener(e -> closeEditor()); // <3>
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns();
        grid.addColumn(User::getUserId).setHeader("№");
        grid.addColumn(User::getLoginUser).setHeader("Login");
        grid.addComponentColumn((item) -> {
            Checkbox checkBox = new Checkbox();
            checkBox.setValue(item.isAdmin());
            checkBox.setReadOnly(true);
            return checkBox;
        }).setHeader("Адміністратор");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editUser(event.getValue()));
    }

    private void updateList() {
        grid.setItems(userService.getUsers());
    }

    private Component getToolbar() {
        Button addUserButton = new Button("Зареєструвати співробітника");
        addUserButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addUserButton.addClickListener(click -> addUser());

        var toolbar = new HorizontalLayout(addUserButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void addUser() {
        grid.asSingleSelect().clear();
        editUser(new User());
    }

    public void editUser(User user) {
        if (user == null) {
            closeEditor();
        } else {
            form.setUser(user);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setUser(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void saveUser(UserForm.SaveEvent event) {
        userService.save(event.getUser());
        updateList();
        closeEditor();
    }

    private void deleteUser(UserForm.DeleteEvent event) {
        userService.delete(event.getUser());
        updateList();
        closeEditor();
    }

}
