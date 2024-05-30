package com.afrosimova.prmanager.views.admin;

import com.afrosimova.prmanager.entities.Survey;
import com.afrosimova.prmanager.services.SurveyService;
import com.afrosimova.prmanager.views.admin.forms.SurveyForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "admin/surveys", layout = AdminLayout.class)
@RolesAllowed("ADMIN")
public class AdminSurveysView extends VerticalLayout {
    private final SurveyService surveyService;
    Grid<Survey> grid = new Grid<>(Survey.class);
    private SurveyForm form;

    public AdminSurveysView(SurveyService surveyService) {
        this.surveyService = surveyService;
        addClassName("admin-survey-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getContent(), getToolbar());
        updateList();
        closeEditor();
    }

    private void configureForm() {
        form = new SurveyForm();
        form.setWidth("25em");
        form.addSaveListener(this::saveSurvey); // <1>
        form.addDeleteListener(this::deleteSurvey); // <2>
        form.addCloseListener(e -> closeEditor()); // <3>
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns();
        grid.addColumn(Survey::getSurveyId).setHeader("Id");
        grid.addColumn(Survey::getSurveyName).setHeader("Назва опитування");
        grid.addColumn(Survey::getDate).setHeader("Дата початку");
        grid.addColumn(Survey::getDateEnd).setHeader("Дата завершення");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event ->
                editSurvey(event.getValue()));
    }

    private void updateList() {
        grid.setItems(surveyService.findAll());
    }

    private Component getToolbar() {
        Button addUserButton = new Button("Створити опитування");
        addUserButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addUserButton.addClickListener(click -> addSurvey());

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

    private void addSurvey() {
        grid.asSingleSelect().clear();
        editSurvey(new Survey());
    }

    public void editSurvey(Survey survey) {
        if (survey == null) {
            closeEditor();
        } else {
            form.setEntity(survey);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setEntity(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void saveSurvey(SurveyForm.SaveEvent event) {
        //surveyService.save(event.getUser());
        updateList();
        closeEditor();
    }

    private void deleteSurvey(SurveyForm.DeleteEvent event) {
        //userService.delete(event.getUser());
        updateList();
        closeEditor();
    }
}
