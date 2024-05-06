package com.afrosimova.prmanager;

import com.afrosimova.prmanager.entities.EmployeeSurvey;
import com.afrosimova.prmanager.services.EmployeeSurveyService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "", layout = MainContentLayout.class)
@RolesAllowed("USER")
public class EmployeeView extends VerticalLayout implements HasDynamicTitle {
    Grid<EmployeeSurvey> grid = new Grid<>(EmployeeSurvey.class);
    TextField nameFilterText = new TextField();
    TextField emailFilterText = new TextField();
    EmployeeSurveyService service;

    public EmployeeView(EmployeeSurveyService employeeSurveyService) {
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
        grid.addClassNames("EMPLOYEE_SURVEY_GRID");
        grid.setSizeFull();
        grid.setColumns();
        grid.addColumn(es -> es.getEmployeeSurveyId()).setHeader("айді");
        grid.addColumns("empCompleted", "manCompleted");
        grid.addColumn(es -> es.getSurvey().getSurveyName()).setHeader("імя");
//        grid.addColumn(contact -> contact.getManager().setHeader("MANAGER_ID");


        grid.getColumns().forEach(col -> col.setAutoWidth(true));

//        grid.asSingleSelect().addValueChangeListener(event ->
//                editAgency(event.getValue()));
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

