package com.afrosimova.prmanager.views;

import com.afrosimova.prmanager.MainContentLayout;
import com.afrosimova.prmanager.entities.EmployeeSurvey;
import com.afrosimova.prmanager.services.EmployeeSurveyService;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "surveys", layout = MainContentLayout.class)
@RolesAllowed("USER")
public class SurveysView extends VerticalLayout implements HasDynamicTitle {
    Grid<EmployeeSurvey> grid = new Grid<>(EmployeeSurvey.class);
    TextField nameFilterText = new TextField();
    TextField emailFilterText = new TextField();
    EmployeeSurveyService service;

    public SurveysView(EmployeeSurveyService employeeSurveyService) {
        this.service = employeeSurveyService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        updateList();

        //H2 h2 = new H2(getTranslation("welcome"));
        //Image image = new Image("./images/pets.png", getTranslation("pets"));

        add(getContent());

//        setSizeFull();
//        setJustifyContentMode(JustifyContentMode.CENTER);
//        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    @Override
    public String getPageTitle() {
        return getTranslation("welcomeTitle");
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns();
        grid.addColumn(EmployeeSurvey::getEmployeeSurveyId).setHeader("id");
        grid.addColumn(es -> es.getSurvey().getSurveyName()).setHeader("Назва");
        grid.addColumn(es -> es.getSurvey().getDate()).setHeader("Дата початку");
        grid.addColumn(es -> es.getSurvey().getDateEnd()).setHeader("Дата завершення");
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
        }).setHeader("Заповнено менеджером");


        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.addItemDoubleClickListener(listener -> {
                    getUI().ifPresent(a -> {
                        a.navigate("survey/" + listener.getItem().getEmployeeSurveyId());
                    });
                }
        );
    }

    private void updateList() {
        var name = nameFilterText.getValue() != null ? nameFilterText.getValue() : "";
        var email = emailFilterText.getValue() != null ? emailFilterText.getValue() : "";
        grid.setItems(service.findEmployeeSurvey(2));
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

