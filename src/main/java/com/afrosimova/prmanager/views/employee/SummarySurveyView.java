package com.afrosimova.prmanager.views.employee;

import com.afrosimova.prmanager.entities.Answers;
import com.afrosimova.prmanager.entities.EmployeeSurvey;
import com.afrosimova.prmanager.entities.SurveyQuestion;
import com.afrosimova.prmanager.services.AnswersService;
import com.afrosimova.prmanager.services.EmployeeSurveyService;
import com.afrosimova.prmanager.services.SurveyService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.RolesAllowed;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.afrosimova.prmanager.entities.AnswersType.EMPLOYEE;
import static com.afrosimova.prmanager.entities.AnswersType.MANAGER;

@Route(value = "summary_survey", layout = MainContentLayout.class)
@RolesAllowed("USER")
public class SummarySurveyView extends VerticalLayout implements HasUrlParameter<String> {
    private final SurveyService surveyService;
    private final AnswersService answersService;
    private final EmployeeSurveyService employeeSurveyService;
    private long employeeSurveyId;
    private Map<Long, Answers> employeeAnswersMap;
    private Map<Long, Answers> managerAnswersMap;
    private EmployeeSurvey employeeSurvey;

    public SummarySurveyView(SurveyService surveyService, AnswersService answersService, EmployeeSurveyService employeeSurveyService) {
        this.surveyService = surveyService;
        this.answersService = answersService;
        this.employeeSurveyService = employeeSurveyService;
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        System.out.println("setParameter");
        try {
            employeeSurveyId = Long.parseLong(s);
        } catch (Exception e) {
            System.out.println("Wrong ID");
        }
    }

    @Override
    public void onAttach(AttachEvent event) {
        this.setMargin(false);
        this.setSpacing(false);
        this.addClassNames(LumoUtility.Border.ALL);
        //this.getStyle().set( "border" , "2px dotted DarkOrange" ) ;
        this.getThemeList().remove("margin");
        this.getThemeList().remove("padding");

        this.setSizeFull();
        this.setClassName(LumoUtility.Overflow.HIDDEN);
        List<SurveyQuestion> questions = surveyService.findPositionQuestionBy(employeeSurveyId);
        List<Answers> answersList = answersService.findAnswers(employeeSurveyId);
        employeeAnswersMap = answersList.stream()
                .filter(a -> EMPLOYEE.name().equalsIgnoreCase(a.getAnswersType()))
                .collect(Collectors.toMap(a -> a.getQuestion().getQuestionId(), a -> a));
        managerAnswersMap = answersList.stream()
                .filter(a -> MANAGER.name().equalsIgnoreCase(a.getAnswersType()))
                .collect(Collectors.toMap(a -> a.getQuestion().getQuestionId(), a -> a));
        employeeSurvey = employeeSurveyService.findById(employeeSurveyId);
        renderLayout(questions);
    }

    private void renderLayout(List<SurveyQuestion> questions) {
        add(renderHeader());
        add(renderQuestions(questions));
add(render1Layout());
add(render2Layout());
        //nbhibbin
    }
    private Component render1Layout() {
        Label feedbackLabel = new Label("Відгук від керівника");
        TextField feedbackField = new TextField();
        feedbackField.setWidthFull();
        this.add(feedbackLabel, feedbackField);
        return feedbackField;
    }
    private Component render2Layout() {
        Label feedbackLabel1 = new Label("Відгук від співробітника");
        TextField feedbackField1 = new TextField();
        feedbackField1.setWidthFull();
        this.add(feedbackLabel1, feedbackField1);
        return feedbackField1;
    }
    private Component renderHeader() {
        final VerticalLayout header = new VerticalLayout();
        header.setMargin(false);
        header.setPadding(false);
        header.setSpacing(false); // Вимкнути відступи між компонентами

        header.getThemeList().remove("margin");
        header.getThemeList().remove("padding");
        header.addClassNames(
                LumoUtility.Background.CONTRAST_10);
        Label label = new Label();
        SimpleDateFormat sdf = new SimpleDateFormat(
                "MM/dd/yyyy");
        label.setText(
                employeeSurvey.getSurvey().getSurveyName() + " (" +
                        sdf.format(employeeSurvey.getSurvey().getDate()) + " - " +
                        sdf.format(employeeSurvey.getSurvey().getDateEnd()) + ") "
        );
        label.addClassNames(
                LumoUtility.TextColor.BODY,
                LumoUtility.AlignContent.CENTER,
                LumoUtility.TextAlignment.CENTER);
        label.setWidthFull();
        header.add(label);

        label = new Label();
        label.setText(
                "для " + employeeSurvey.getEmployee().getLastName() + " " +
                        employeeSurvey.getEmployee().getFirstName()
        );
        label.addClassNames(
                LumoUtility.TextColor.BODY,
                LumoUtility.AlignContent.CENTER,
                LumoUtility.TextAlignment.CENTER);
        label.setWidthFull();
        header.add(label);
        return header;
    }

    private Component renderQuestions(List<SurveyQuestion> questions) {
        VerticalLayout body = new VerticalLayout();
        body.addClassNames(LumoUtility.Overflow.AUTO);
        body.setSizeFull();
        body.setSpacing(false);
        questions.sort(Comparator.comparing(SurveyQuestion::getQuestionOrder));
        for (SurveyQuestion question : questions) {
            Answers employeeAnswers = employeeAnswersMap.get(question.getQuestion().getQuestionId());
            Answers managerAnswers = managerAnswersMap.get(question.getQuestion().getQuestionId());
            switch (question.getQuestion().getType()) {
                case 1 -> renderTextQuestion(body, question, employeeAnswers, managerAnswers);
                case 2 -> renderCheckBoxQuestion(body, question, employeeAnswers, managerAnswers);
                case 3 -> renderRadioButtonQuestion(body, question, employeeAnswers, managerAnswers);
                case 4 -> renderDropdownQuestion(body, question, employeeAnswers, managerAnswers);
            }
            ;
        }
        return body;
    }

    private Component renderQuestion(SurveyQuestion question, Collection<Component> questionComponents) {
        VerticalLayout layout = new VerticalLayout();
        layout.addClassNames(LumoUtility.Border.BOTTOM);
        layout.setWidthFull();

        Label label = new Label();
        label.addClassNames(LumoUtility.TextColor.PRIMARY, LumoUtility.FontSize.MEDIUM);
        label.setText(question.getQuestion().getText());
        layout.add(label);

        Label descriptionLabel = new Label();
        descriptionLabel.addClassNames(LumoUtility.TextColor.BODY, LumoUtility.FontSize.XSMALL);
        descriptionLabel.setText(question.getQuestion().getDescription());
        descriptionLabel.setWidthFull();
        layout.add(descriptionLabel);

        layout.add(questionComponents);

        return layout;
    }

    private void renderDropdownQuestion(HasComponents parent, SurveyQuestion question, Answers employeeAnswers, Answers managerAnswers) {
        Select<String> dropdown = new Select<>();
        dropdown.setLabel(question.getQuestion().getText());
        dropdown.setItems("1", "2", "3", "4", "5");
        dropdown.setWidthFull();
        dropdown.setReadOnly(true);
        parent.add(renderQuestion(question, List.of(dropdown)));
    }

    private void renderCheckBoxQuestion(HasComponents parent, SurveyQuestion question, Answers employeeAnswers, Answers managerAnswers) {
        Checkbox checkbox = new Checkbox(question.getQuestion().getText());
        checkbox.setReadOnly(true);
        parent.add(renderQuestion(question, List.of(checkbox)));
    }

    private void renderRadioButtonQuestion(HasComponents parent, SurveyQuestion question, Answers employeeAnswers, Answers managerAnswers) {
        RadioButtonGroup<String> employeeOptionsGroup = new RadioButtonGroup<>();
        employeeOptionsGroup.setWidthFull();
        employeeOptionsGroup.setItems(QuestionComponents.getRadioGroupIds(question.getQuestion().getAnswers()));
        var values = QuestionComponents.getRadioGroupValues(question.getQuestion().getAnswers());
        employeeOptionsGroup.setRenderer(new TextRenderer<>((mn) -> {
            int index = Integer.parseInt(mn) - 1;
            return values.get(index);
        }));
        if (employeeAnswers != null && employeeAnswers != null) {
            employeeOptionsGroup.setValue(employeeAnswers.getText());
        }
        employeeOptionsGroup.setReadOnly(true);
        employeeOptionsGroup.setLabel("Самооцінка ");

        RadioButtonGroup<String> managerOptionsGroup = new RadioButtonGroup<>();
        managerOptionsGroup.setWidthFull();
        managerOptionsGroup.setItems(QuestionComponents.getRadioGroupIds(question.getQuestion().getAnswers()));
        managerOptionsGroup.setRenderer(new TextRenderer<>((mn) -> {
            int index = Integer.parseInt(mn) - 1;
            return values.get(index);
        }));
        if (managerAnswers != null && managerOptionsGroup != null) {
            managerOptionsGroup.setValue(managerAnswers.getText());
        }
        managerOptionsGroup.setReadOnly(true);
        managerOptionsGroup.setLabel("Оцінка керівника ");

        if (employeeAnswers != null && employeeAnswers.getText() != null &&
                managerAnswers != null && managerAnswers.getText() != null) {
            int empAnswerValue = Integer.parseInt(employeeAnswers.getText());
            int manAnswerValue = Integer.parseInt(managerAnswers.getText());
            if (empAnswerValue < manAnswerValue) {
                employeeOptionsGroup.getStyle().set("background-color","Salmon");
                managerOptionsGroup.getStyle().set("background-color","Salmon");
            } else if (empAnswerValue > manAnswerValue) {
                employeeOptionsGroup.getStyle().set("background-color","PaleGreen");
                managerOptionsGroup.getStyle().set("background-color","PaleGreen");
            }
        }

        parent.add(renderQuestion(question, List.of(employeeOptionsGroup, managerOptionsGroup)));
    }

    private void renderTextQuestion(HasComponents parent, SurveyQuestion question, Answers employeeAnswers, Answers managerAnswers) {
        Label empLabel = new Label("Самооцінка");
        TextField employeeText = new TextField();
        employeeText.setWidthFull();
        if (employeeAnswers != null) {
            employeeText.setValue(employeeAnswers.getText());
        }
        employeeText.setReadOnly(true);

        Label manLabel = new Label("Оцінка керівника");
        TextField managerText = new TextField();
        managerText.setWidthFull();
        if (managerAnswers != null) {
            managerText.setValue(managerAnswers.getText());
        }
        managerText.setReadOnly(true);

        parent.add(renderQuestion(question, List.of(empLabel, employeeText, manLabel, managerText)));
    }
}
