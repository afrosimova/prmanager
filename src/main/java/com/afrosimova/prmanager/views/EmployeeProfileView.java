package com.afrosimova.prmanager.views;

import com.afrosimova.prmanager.entities.Employee;
import com.afrosimova.prmanager.services.EmployeeService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.component.button.Button;
import java.io.ByteArrayInputStream;

public class EmployeeProfileView extends VerticalLayout {

    private EmployeeService employeeService;

    public EmployeeProfileView(EmployeeService employeeService) {
        this.employeeService = employeeService;

        Employee employee = (Employee) employeeService.findEmployee(1);  // Assume ID=1 for demonstration

        HorizontalLayout profileHeader = new HorizontalLayout();
        profileHeader.setWidthFull();
        profileHeader.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        //Image profileImage = new Image();
//        if (employee.getPhoto() != null) {
//            StreamResource resource = new StreamResource("profile.jpg",
//                    () -> new ByteArrayInputStream(employee.getPhoto()));
//            profileImage.setSrc(resource);
//        } else {
//            profileImage.setSrc("images/default-user.png"); // Default image path
//        }
//        profileImage.setHeight("100px");
//        profileImage.setWidth("100px");

        VerticalLayout infoLayout = new VerticalLayout();
        Label firstNameLabel = new Label("First Name: " + employee.getFirstName());
        Label lastNameLabel = new Label("Last Name: " + employee.getLastName());
        Label emailLabel = new Label("Email: " + employee.getEmail());
        //Label positionLabel = new Label("Position: " + employee.getPosition());

        infoLayout.add(firstNameLabel, lastNameLabel, emailLabel /*positionLabel*/);

        profileHeader.add(infoLayout);
        add(profileHeader);
    }
}
