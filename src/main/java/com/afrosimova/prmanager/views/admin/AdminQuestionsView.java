package com.afrosimova.prmanager.views.admin;

import com.afrosimova.prmanager.entities.Question;
import com.afrosimova.prmanager.entities.Survey;
import com.afrosimova.prmanager.services.QuestionService;
import com.afrosimova.prmanager.services.SurveyService;
import com.afrosimova.prmanager.views.admin.forms.QuestionForm;
import com.afrosimova.prmanager.views.admin.forms.SurveyForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "admin/questions", layout = AdminLayout.class)
@RolesAllowed("ADMIN")
public class AdminQuestionsView extends VerticalLayout {
    private final QuestionService questionService;
    Grid<Question> grid = new Grid<>(Question.class);
    private QuestionForm form;

    public AdminQuestionsView(QuestionService questionService) {
        this.questionService = questionService;
        addClassName("admin-question-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getContent(), getToolbar());
        updateList();
        closeEditor();
    }

    private void configureForm() {
        form = new QuestionForm();
        form.setWidth("25em");
        form.addSaveListener(this::saveQuestion); // <1>
        form.addDeleteListener(this::deleteQuestion); // <2>
        form.addCloseListener(e -> closeEditor()); // <3>
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns();
        grid.addColumn(Question::getText).setHeader("Питання");
        var descriptionColumn = grid.addColumn(Question::getDescription).setHeader("Опис");
        grid.addColumn(Question::getType).setHeader("Тип");
        grid.addColumn(Question::getType).setHeader("№ відповідей");
        //grid.getColumns().forEach(col -> col.setAutoWidth(true));
        //descriptionColumn.setAutoWidth(false);
        grid.asSingleSelect().addValueChangeListener(event ->
                editQuestion(event.getValue()));
    }

    private void updateList() {
        grid.setItems(questionService.findAll());
    }

    private Component getToolbar() {
        Button addQuestionButton = new Button("Створити питання");
        addQuestionButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addQuestionButton.addClickListener(click -> addQuestion());

        var toolbar = new HorizontalLayout(addQuestionButton);
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

    private void addQuestion() {
        grid.asSingleSelect().clear();
        editQuestion(new Question());
    }

    public void editQuestion(Question question) {
        if (question == null) {
            closeEditor();
        } else {
            form.setEntity(question);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setEntity(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void saveQuestion(SurveyForm.SaveEvent event) {
        //surveyService.save(event.getUser());
        updateList();
        closeEditor();
    }

    private void deleteQuestion(SurveyForm.DeleteEvent event) {
        //userService.delete(event.getUser());
        updateList();
        closeEditor();
    }
}

