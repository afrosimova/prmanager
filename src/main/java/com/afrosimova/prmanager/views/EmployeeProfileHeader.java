//package com.afrosimova.prmanager.views;
//
//
//    import com.afrosimova.prmanager.entities.Position;
//    import com.afrosimova.prmanager.entities.User;
//    import com.afrosimova.prmanager.services.EmployeeService;
//    import com.afrosimova.prmanager.services.EmployeeSurveyService;
//    import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.html.Image;
//import com.vaadin.flow.component.html.Label;
//import com.vaadin.flow.server.StreamResource;
//
//import java.io.ByteArrayInputStream;
//
//    public class EmployeeProfileHeader extends VerticalLayout {
//        EmployeeService service;
//
//        public EmployeeProfileHeader(EmployeeService employeeService, byte[] photoBytes) {
//            this.service = employeeService;
//
//            // Створення горизонтального контейнера для шапки
//            HorizontalLayout header = new HorizontalLayout();
//            header.setWidthFull();
//            header.setAlignItems(Alignment.CENTER);
//
//            // Додавання фотографії
//            StreamResource photoResource = new StreamResource("profile-photo", () -> new ByteArrayInputStream(photoBytes));
//            Image photo = new Image(photoResource, "Profile Photo");
//            photo.setHeight("100px");
//            photo.setWidth("100px");
//
//            // Створення міток для інформації про працівника
////            Label nameLabel = new Label(firstName + " " + lastName);
////            nameLabel.getStyle().set("font-weight", "bold");
////            nameLabel.getStyle().set("margin-left", "20px");
////
////            Label nicknameLabel = new Label("Nickname: " + nickname);
////            nicknameLabel.getStyle().set("margin-left", "20px");
////
////            Label positionLabel = new Label("Position: " + position);
////            positionLabel.getStyle().set("margin-left", "20px");
//
//            // Додавання компонентів до горизонтального контейнера
//            header.add(photo, nameLabel, nicknameLabel, positionLabel);
//
//            // Додавання горизонтального контейнера до основного вертикального контейнера
//            add(header);
//        }
//    }
//
//}
