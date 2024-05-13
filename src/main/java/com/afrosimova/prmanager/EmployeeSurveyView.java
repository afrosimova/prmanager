//package com.afrosimova.prmanager;
//
//import com.afrosimova.prmanager.entities.EmployeeSurvey;
//import com.afrosimova.prmanager.repositories.EmployeeSurveyRepository;
//import com.afrosimova.prmanager.services.EmployeeSurveyService;
//import com.vaadin.flow.component.Component;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.grid.Grid;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.data.value.ValueChangeMode;
//import com.vaadin.flow.router.PageTitle;
//import com.vaadin.flow.router.Route;
//import com.vaadin.flow.spring.annotation.SpringComponent;
//import jakarta.annotation.security.PermitAll;
//import org.springframework.context.annotation.Scope;
//
//
//@SpringComponent
//@Scope("prototype")
//@PermitAll
////@Route(value = "", layout = MainLayout.class)
//@PageTitle("Contacts | Vaadin CRM")
//public class EmployeeSurveyView extends VerticalLayout {
//    Grid<EmployeeSurvey> grid = new Grid<>(EmployeeSurvey.class);
//    TextField nameFilterText = new TextField();
//    TextField emailFilterText = new TextField();
//    EmployeeSurveyService service;
//
//    public EmployeeSurveyView(EmployeeSurveyService employeeSurveyService) {
//        this.service = employeeSurveyService;
//        addClassName("list-view");
//        setSizeFull();
//        configureGrid();
//        //configureForm();
//
//        add(getToolbar(), getContent());
//        updateList();
//        //closeEditor();
//    }
//
//    private HorizontalLayout getContent() {
//        HorizontalLayout content = new HorizontalLayout(grid/*, form*/);
//        content.setFlexGrow(2, grid);
//        //content.setFlexGrow(1, form);
//        content.addClassNames("content");
//        content.setSizeFull();
//        return content;
//    }
//
////    private void configureForm() {
////        form = new AgencyForm(service.findAllCompanias());
////        form.setWidth("25em");
////        form.addSaveListener(this::saveAgency); // <1>
////        form.addDeleteListener(this::deleteAgency); // <2>
////        form.addCloseListener(e -> closeEditor()); // <3>
////    }
////
////    private void saveAgency(AgencyForm.SaveEvent event) {
////        service.saveAgency(event.getAgency());
////        updateList();
////        closeEditor();
////    }
////
////    private void deleteAgency(AgencyForm.DeleteEvent event) {
////        service.deleteAgency(event.getAgency());
////        updateList();
////        closeEditor();
////    }
//
//    private void configureGrid() {
//        grid.addClassNames("EMPLOYEE_SURVEY_GRID");
//        grid.setSizeFull();
//        grid.setColumns("EMPLOYEE_SURVEY_ID", "MANAGER_ID", "EMP_COMPLETED", "MAN_COMPLETED", "EMPLOYEE_ID", "SURVEY_ID");
////        grid.addColumn(contact -> contact.getManager().setHeader("MANAGER_ID"));
////        grid.addColumn(contact -> contact.getManager().setHeader("MANAGER_ID");
//
//
//        grid.getColumns().forEach(col -> col.setAutoWidth(true));
//
////        grid.asSingleSelect().addValueChangeListener(event ->
////                editAgency(event.getValue()));
//    }
//
//    private Component getToolbar() {
//        nameFilterText.setPlaceholder("Filter by company...");
//        nameFilterText.setClearButtonVisible(true);
//        nameFilterText.setValueChangeMode(ValueChangeMode.LAZY);
//        nameFilterText.addValueChangeListener(e -> updateList());
//        emailFilterText.setPlaceholder("Filter by city...");
//        emailFilterText.setClearButtonVisible(true);
//        emailFilterText.setValueChangeMode(ValueChangeMode.LAZY);
//        emailFilterText.addValueChangeListener(e -> updateList());
//
////        Button addContactButton = new Button("Add agency");
////        addContactButton.addClickListener(click -> addContact());
//
//        var toolbar = new HorizontalLayout(nameFilterText, emailFilterText/*, addContactButton*/);
//        toolbar.addClassName("toolbar");
//        return toolbar;
//    }
//
////    public void editAgency(EmployeeSurvey employeeSurvey) {
////        if (employeeSurvey == null) {
////            closeEditor();
////        }
////        else {
////            form.setAgency(agency);
////            form.setVisible(true);
////            addClassName("editing");
////        }
////    }
//
////    private void closeEditor() {
////        form.setAgency(null);
////        form.setVisible(false);
////        removeClassName("editing");
////    }
//
////    private void addContact() {
////        grid.asSingleSelect().clear();
////        editAgency(new EmployeeSurvey());
////    }
//
//    private void updateList() {
//        var name = nameFilterText.getValue() != null ? nameFilterText.getValue() : "";
//        var email = emailFilterText.getValue() != null ? emailFilterText.getValue() : "";
//        grid.setItems(service.findEmployeeSurvey(2));
//    }
//}