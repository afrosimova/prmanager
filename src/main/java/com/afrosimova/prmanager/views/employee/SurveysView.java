package com.afrosimova.prmanager.views.employee;

import com.afrosimova.prmanager.entities.AnswersType;
import com.afrosimova.prmanager.entities.EmployeeSurvey;
import com.afrosimova.prmanager.security.SecurityService;
import com.afrosimova.prmanager.services.EmployeeSurveyService;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HasDynamicTitle;

import java.util.List;

import static com.afrosimova.prmanager.entities.AnswersType.MANAGER;

public abstract class SurveysView extends VerticalLayout implements HasDynamicTitle {
    Grid<EmployeeSurvey> grid = new Grid<>(EmployeeSurvey.class);
    EmployeeSurveyService employeeSurveyService;
    SecurityService securityService;

    public SurveysView(EmployeeSurveyService employeeSurveyService, SecurityService securityService) {
        this.employeeSurveyService = employeeSurveyService;
        this.securityService = securityService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        updateList();

        add(getContent());
    }

    @Override
    public String getPageTitle() {
        return getTranslation("welcomeTitle");
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns();
        grid.addColumn(EmployeeSurvey::getEmployeeSurveyId).setHeader("№");
        grid.addColumn(es -> es.getSurvey().getSurveyName()).setHeader("Назва опитування");
        grid.addColumn(es -> es.getSurvey().getDate()).setHeader("Дата початку");
        grid.addColumn(es -> es.getSurvey().getDateEnd()).setHeader("Дата завершення");
        if (getUserType() == MANAGER) {
            grid.addColumn(es -> es.getEmployee().getFullName()).setHeader("Співробітник");
            grid.addColumn(es -> es.getEmployee().getPosition().getPositionName()).setHeader("Посада");
        }
        if (getUserType() == AnswersType.EMPLOYEE) {
            grid.addColumn(es -> es.getManager().getFullName()).setHeader("Керівник");
        }
        grid.addComponentColumn((item) -> {
            Checkbox checkBox = new Checkbox();
            checkBox.setValue(item.isEmpCompleted());
            checkBox.setReadOnly(true);
            return checkBox;
        }).setHeader("Заповнено співробітником");
        grid.addComponentColumn((item) -> {
            Checkbox checkBox = new Checkbox();
            checkBox.setValue(item.isManCompleted());
            checkBox.setReadOnly(true);
            return checkBox;
        }).setHeader("Заповнено керівником");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.addItemDoubleClickListener(listener -> {
            String path;
            if (listener.getItem().isEmpCompleted() && listener.getItem().isManCompleted()) {
                path = "summary_survey/";
            } else {
                path = getUserType() == MANAGER ? "manager_survey/" : "employee_survey/";
            }
            getUI().ifPresent(a -> {
                a.navigate(path + listener.getItem().getEmployeeSurveyId());
            });
        });
    }

    public abstract List<EmployeeSurvey> findSurveys();

    public abstract AnswersType getUserType();

    private void updateList() {
        grid.setItems(findSurveys());
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid/*, form*/);
        content.setFlexGrow(2, grid);
        //content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

}

