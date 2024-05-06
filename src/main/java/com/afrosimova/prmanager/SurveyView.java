package com.afrosimova.prmanager;

import com.afrosimova.prmanager.entities.EmployeeSurvey;
import com.afrosimova.prmanager.entities.Question;
import com.afrosimova.prmanager.entities.Survey;
import com.afrosimova.prmanager.services.SurveyService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

//package org.springframework.samples.petclinic.ui.view;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import javax.swing.text.html.Option;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Route(value = "survey", layout = MainContentLayout.class)
@RolesAllowed("USER")

//public class SurveyView extends VerticalLayout {
//
//    public SurveyView() {
//        // Throw a server exception, so as ErrorHandlerView is displayed.
//        throw new RuntimeException("Expected: controller used to showcase what happens when an exception is thrown");
//    }
    public class SurveyView extends VerticalLayout{
    TextField questionText1 = new TextField("blabla");

    private TextField questionField;
    private RadioButtonGroup<String> optionsGroup;

    //Grid<EmployeeSurvey> grid = new Grid<>(EmployeeSurvey.class);
    TextField nameFilterText = new TextField();
    TextField emailFilterText = new TextField();
//        TextField title = new TextField("Title");
//        TextField preamble = new TextField("Preamble");
//        TextField description = new TextField("Description");
//        TextField category = new TextField("Category");
        SurveyService surveyService;
        //QuestionService questionService;
        Button submit = new Button("Submit");
        public SurveyView(SurveyService surveyService/*,QuestionService questionService*/) {
            this.surveyService = surveyService;
            //this.questionService=questionService;

            questionField = new TextField("Питання:");
            questionField.setWidth("400px");

            // Група радіокнопок для варіантів відповіді
            optionsGroup = new RadioButtonGroup<>();
            optionsGroup.setLabel("Виберіть відповідь:");
            optionsGroup.setItems("Варіант 1", "Варіант 2", "Варіант 3", "Варіант 4");
            optionsGroup.getStyle().set("flexDirection", "column"); // Розташувати по вертикалі

            // Кнопка для підтвердження відповіді
            Button submitButton = new Button("Підтвердити", event -> {
                String question = questionField.getValue();
                String selectedOption = optionsGroup.getValue();
                System.out.println("Питання: " + question);
                System.out.println("Обраний варіант відповіді: " + selectedOption);
                // Тут можна додати логіку обробки відповіді
            });

            // Додавання компонентів до макету
            add(questionField, optionsGroup, submitButton);

            Select<String> dropdown = new Select<>();
            dropdown.setLabel("Оберіть відповідь:");
            dropdown.setItems("Варіант 1", "Варіант 2", "Варіант 3", "Варіант 4", "Варіант 5");

            // Додавання випадаючого списку на макет


            add(dropdown);
questionText1.setReadOnly(true);

//            Survey survey = surveyService.findSurvey(1L);
//            title.setValue(survey.getTitle());
//            title.setReadOnly(true);
//            preamble.setValue(survey.getPreamble());
//            preamble.setReadOnly(true);
//            description.setValue(survey.getDescription());
//            description.setReadOnly(true);
//            category.setValue(survey.getCategory().toString());
//            category.setReadOnly(true);
//            FormLayout layout = new FormLayout();
//            layout.add(title,preamble,description,category);
//            layout.setResponsiveSteps(new FormLayout.ResponsiveStep("0",1));
//
//
//            add(layout);
//
//            for(Question question: questionService.findQuestion(survey.getId())){
//                add(createQuestionRow(question));
//            }
//
//            getChildren().forEach(component->{
//                System.out.println(component.getElement().toString());
//            });
//            submit.addClickListener(buttonClickEvent -> {
//
//            });
//
      }

//
//
//        private Component createQuestionRow(Question question) {
//            FormLayout layout =new FormLayout();
//            List<String> answers = new ArrayList<>();
//            H5 qOrder = new H5(String.valueOf(question.getQuestionOrder()));
//            H6 statement = new H6(question.getStatement());
//            HorizontalLayout statementLayout = new HorizontalLayout();
//            statementLayout.add(qOrder,statement);
//            statementLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
//            RadioButtonGroup<String> optionsRadio = new RadioButtonGroup<>();
//            TextField shortAnswer = new TextField();
//            List<String> list;
//            Checkbox required = new Checkbox("Is Required");
//            required.setValue(question.isRequired());
//            required.isReadOnly();
//            VerticalLayout options = new VerticalLayout();
//            if(question.getOption()== Option.linearTen){
//                optionsRadio.setItems("10","9","8","7","6","5","4","3","2","1");
//                options.add(optionsRadio);
//            }
//            else if (question.getOption()== Option.AnnotatedFour) {
//                optionsRadio.setItems("Excellent","Very good","good","bad");
//                options.add(optionsRadio);
//            }
//            else if ( question.getOption()== Option.shortAnswer){
//                options.add(shortAnswer);
//            }
//            optionsRadio.addValueChangeListener(click->{
//                answers.add(click.getValue());
//                System.out.println(click.getValue());
//            });
//
//            layout.add(statementLayout,options,required);
//            layout.setResponsiveSteps(new FormLayout.ResponsiveStep("0",2));
//            layout.setColspan(statementLayout,2);
//            return layout;
//
//        }
//    private void updateList() {
//        var name = nameFilterText.getValue() != null ? nameFilterText.getValue() : "";
//        var email = emailFilterText.getValue() != null ? emailFilterText.getValue() : "";
//        grid.setItems((EmployeeSurvey) surveyService.findPositionQuestionBy(1));
//    }

}

