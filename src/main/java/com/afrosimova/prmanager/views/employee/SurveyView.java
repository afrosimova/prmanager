package com.afrosimova.prmanager.views.employee;

import com.afrosimova.prmanager.entities.Answers;
import com.afrosimova.prmanager.entities.AnswersType;
import com.afrosimova.prmanager.entities.EmployeeSurvey;
import com.afrosimova.prmanager.entities.SurveyQuestion;
import com.afrosimova.prmanager.services.AnswersService;
import com.afrosimova.prmanager.services.EmployeeSurveyService;
import com.afrosimova.prmanager.services.SurveyService;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.theme.lumo.LumoUtility;
import lombok.RequiredArgsConstructor;
import org.codehaus.plexus.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.afrosimova.prmanager.entities.AnswersType.EMPLOYEE;
import static com.afrosimova.prmanager.entities.AnswersType.MANAGER;

@RequiredArgsConstructor
public abstract class SurveyView extends VerticalLayout implements HasUrlParameter<String> {
    private final SurveyService surveyService;
    private final AnswersService answersService;
    private final EmployeeSurveyService employeeSurveyService;
    private long employeeSurveyId;
    private Map<Long, SurveyQuestion> questionMap;
    private Map<Long, Answers> answersMap;
    private Map<Long, Component> answersComponentMap = new HashMap<>();
    private EmployeeSurvey employeeSurvey;
    private Button submitButton, finishButton;

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
        answersComponentMap.clear();
        List<SurveyQuestion> questions = surveyService.findPositionQuestionBy(employeeSurveyId);
        questionMap = questions.stream().collect(Collectors.toMap(SurveyQuestion::getPositionQuestionId, q -> q));
        List<Answers> answersList = answersService.findAnswers(employeeSurveyId, getUserType().name());
        answersMap = answersList.stream().collect(Collectors.toMap(a -> a.getQuestion().getQuestionId(), a -> a));
        employeeSurvey = employeeSurveyService.findById(employeeSurveyId);
        renderLayout(questions);
    }

    private void renderLayout(List<SurveyQuestion> questions) {
        add(renderHeader());
        add(renderQuestions(questions));
        add(renderFooter());
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
                "dd/MM/yyyy");

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
        label.getStyle().set("font-weight", "bold");
        label.addClassNames(
                LumoUtility.TextColor.BODY,
                LumoUtility.AlignContent.CENTER,
                LumoUtility.TextAlignment.CENTER);
        label.setWidthFull();
        header.add(label);
//
//        HorizontalLayout surveyLine = new HorizontalLayout();
//        surveyLine.add(
//                renderHeaderComponent("Опитування:", employeeSurvey.getSurvey().getSurveyName()),
//                renderHeaderComponent("Дата початку:", employeeSurvey.getSurvey().getDate().toString()),
//                renderHeaderComponent("Дата завеершення:", employeeSurvey.getSurvey().getDateEnd().toString())
//        );
//        //header.add(surveyLine);
//
//        HorizontalLayout employeeLine = new HorizontalLayout();
//        employeeLine.add(
//                renderHeaderComponent("Співробітник:", employeeSurvey.getEmployee().getLastName() + " " +
//                        employeeSurvey.getEmployee().getFirstName()),
//                renderHeaderComponent("Посада:", employeeSurvey.getEmployee().getPosition().getPositionName()),
//                renderHeaderComponent("Email:", employeeSurvey.getEmployee().getEmail())
//        );
//        //header.add(employeeLine);
//
//        HorizontalLayout managerLine = new HorizontalLayout();
//        managerLine.add(
//                renderHeaderComponent("Менеджер:", employeeSurvey.getManager().getLastName() + " " +
//                        employeeSurvey.getEmployee().getFirstName()),
//                renderHeaderComponent("Посада:", employeeSurvey.getManager().getPosition().getPositionName()),
//                renderHeaderComponent("Email:", employeeSurvey.getManager().getEmail())
//        );
//        //header.add(managerLine);
        return header;
    }

    public abstract AnswersType getUserType();

    private Component renderHeaderComponent(String labelName, String value) {
        TextField component = new TextField();
        component.getStyle().set("label-position", "left");
        component.addThemeName("label-left");
        component.setLabel(labelName);
        component.setValue(value);
        return component;
    }

    private Component renderFooter() {
        final HorizontalLayout footer = new HorizontalLayout();
        footer.addClassNames(
                LumoUtility.Background.CONTRAST_10,
                LumoUtility.TextColor.BODY,
                LumoUtility.Padding.SMALL,
                LumoUtility.AlignContent.CENTER,
                LumoUtility.TextAlignment.CENTER);
        footer.setWidthFull();
        //footer.getStyle().set( "border" , "2px dotted DarkOrange" ) ;

        footer.setMargin(false);
        footer.setPadding(false);
        footer.getThemeList().remove("margin");

        submitButton = new Button("Зберегти", e -> {
            saveAnswers();
            enableButton(submitButton, false);
        });

        finishButton = new Button("Готово");
        finishButton.addClickListener(e -> {
            saveAnswers();
            enableButton(submitButton, false);
            employeeSurveyService.finilize(getUserType(), employeeSurveyId);
            enableButton(finishButton, false);
            answersComponentMap.values().stream()
                    .forEach(a -> {
                        ((HasValueAndElement) a).setReadOnly(true);
                    });
        });
        enableButton(submitButton, false);
        enableButton(finishButton, !isReadOnly());
        footer.add(submitButton, finishButton);
        return footer;
    }

    private void enableButton(Button button, boolean enabled) {
        if (button == null) {
            return;
        }
        if (!enabled) {
            button.setEnabled(false);
            button.removeClassNames(LumoUtility.Background.PRIMARY,
                    LumoUtility.TextColor.PRIMARY_CONTRAST);
            button.addClassNames(LumoUtility.Background.BASE,
                    LumoUtility.TextColor.DISABLED);
        } else {
            button.setEnabled(true);
            button.removeClassNames(LumoUtility.Background.BASE,
                    LumoUtility.TextColor.DISABLED);
            button.addClassNames(LumoUtility.Background.PRIMARY,
                    LumoUtility.TextColor.PRIMARY_CONTRAST);
        }
    }

    private void saveAnswers() {
        answersComponentMap.entrySet().stream().forEach(es -> {
            String value = null;
            if (es.getValue() instanceof RadioButtonGroup<?>) {
                RadioButtonGroup<String> rbg = (RadioButtonGroup<String>) es.getValue();
                value = rbg.getValue();
            } else if (es.getValue() instanceof TextField textField) {
                value = textField.getValue();
            }
            if (StringUtils.isNotEmpty(value)) {
                Answers oldAnswers = answersMap.get(es.getKey());
                if (oldAnswers != null) {
                    oldAnswers.setText(value);
                    answersService.update(oldAnswers);
                } else {
                    Answers newAnswers = answersService.create(employeeSurveyId, es.getKey(), getUserType().name(), value);
                    answersMap.put(es.getKey(), newAnswers);
                }
                System.out.println("Saved");
            }
        });
    }

    private Component renderQuestions(List<SurveyQuestion> questions) {
        VerticalLayout body = new VerticalLayout();
        body.addClassNames(LumoUtility.Overflow.AUTO);
        body.setSizeFull();
        questions.sort(Comparator.comparing(SurveyQuestion::getQuestionOrder));
        for (SurveyQuestion question : questions) {
            Answers answers = answersMap.get(question.getQuestion().getQuestionId());
            Component questionComponent = switch (question.getQuestion().getType()) {
                case 1 -> renderTextQuestion(question, answers);
                case 2 -> renderCheckBoxQuestion(question, answers);
                case 3 -> renderRadioButtonQuestion(question, answers);
                case 4 -> renderDropdownQuestion(question, answers);
                default -> null;
            };
            if (questionComponent != null) {
                ((HasValue) questionComponent).setReadOnly(isReadOnly());
                ((AbstractField) questionComponent).addValueChangeListener(e -> onChange());
                body.add(renderQuestion(question, questionComponent));
                answersComponentMap.put(question.getQuestion().getQuestionId(), questionComponent);
            }
        }
        return body;
    }

    private Component renderQuestion(SurveyQuestion question, Component questionComponent) {
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

        layout.add(questionComponent);

        return layout;
    }

    private void onChange() {
        enableButton(submitButton, true);
        enableButton(finishButton, true);
    }

    private boolean isReadOnly() {
        return (getUserType() == MANAGER && employeeSurvey.isManCompleted()) ||
            (getUserType() == EMPLOYEE && employeeSurvey.isEmpCompleted());
    }

    private Component renderDropdownQuestion(SurveyQuestion question, Answers answers) {
        Select<String> dropdown = new Select<>();
        //Label label = new Label(question.getQuestion().getText());
        dropdown.setLabel(question.getQuestion().getText());
        dropdown.setItems("1", "2", "3", "4", "5");
        dropdown.setWidthFull();
        if (isReadOnly()) {
            dropdown.setReadOnly(true);
        }
        dropdown.addValueChangeListener(e -> onChange());

        return dropdown;
    }

    private Component renderCheckBoxQuestion(SurveyQuestion question, Answers answers) {
        Checkbox checkbox = new Checkbox(question.getQuestion().getText());
        if (isReadOnly()) {
            checkbox.setReadOnly(true);
        }
        checkbox.addValueChangeListener(e -> onChange());

        return checkbox;
    }

    private Component renderRadioButtonQuestion(SurveyQuestion question, Answers answers) {
        RadioButtonGroup<String> optionsGroup = new RadioButtonGroup<>();
        optionsGroup.setWidthFull();
        optionsGroup.setItems(QuestionComponents.getRadioGroupIds(question.getQuestion().getAnswers()));
        var values = QuestionComponents.getRadioGroupValues(question.getQuestion().getAnswers());
        optionsGroup.setRenderer(new TextRenderer<>((mn) -> {
            int index = Integer.parseInt(mn) - 1;
            return values.get(index);
        }));
        if (answers != null) {
            optionsGroup.setValue(answers.getText());
        }
        optionsGroup.setReadOnly(isReadOnly());
        optionsGroup.addValueChangeListener(e -> onChange());
        return optionsGroup;
    }

    private Component renderTextQuestion(SurveyQuestion question, Answers answers) {
        TextField textField = new TextField();
        textField.setWidthFull();
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.addValueChangeListener(e -> onChange());
        if (answers != null) {
            textField.setValue(answers.getText());
        }
        if (isReadOnly()) {
            textField.setReadOnly(true);
        }
        return textField;
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

