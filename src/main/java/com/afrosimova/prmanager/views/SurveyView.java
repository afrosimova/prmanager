package com.afrosimova.prmanager.views;

import com.afrosimova.prmanager.MainContentLayout;
import com.afrosimova.prmanager.entities.Answers;
import com.afrosimova.prmanager.entities.EmployeeSurvey;
import com.afrosimova.prmanager.entities.SurveyQuestion;
import com.afrosimova.prmanager.services.AnswersService;
import com.afrosimova.prmanager.services.EmployeeSurveyService;
import com.afrosimova.prmanager.services.SurveyService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
import lombok.RequiredArgsConstructor;
import org.codehaus.plexus.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.afrosimova.prmanager.entities.AnswersType.EMPLOYEE;

@Route(value = "survey", layout = MainContentLayout.class)
@RolesAllowed("USER")
@RequiredArgsConstructor
public class SurveyView extends VerticalLayout implements HasUrlParameter<String> {
    private final SurveyService surveyService;
    private final AnswersService answersService;
    private final EmployeeSurveyService employeeSurveyService;
    private long employeeSurveyId;
    private Map<Long, SurveyQuestion> questionMap;
    private Map<Long, Answers> answersMap;
    private Map<Long, Component> answersComponentMap = new HashMap<>();

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
        List<Answers> answersList = answersService.findAnswers(employeeSurveyId, EMPLOYEE.name());
        answersMap = answersList.stream().collect(Collectors.toMap(a -> a.getQuestion().getQuestionId(), a -> a));

        renderLayout(questions);
    }

    private void renderLayout(List<SurveyQuestion> questions) {
        add(renderHeader());
        add(renderQuestions(questions));
        add(renderFooter());
    }

    private Component renderHeader() {
        EmployeeSurvey employeeSurvey = employeeSurveyService.findById(employeeSurveyId);
        final VerticalLayout header = new VerticalLayout();
        header.setMargin(false);
        header.setPadding(false);
        header.getThemeList().remove("margin");
        header.getThemeList().remove("padding");
        header.addClassNames(
                LumoUtility.Background.CONTRAST_10);
        Label label = new Label();
        label.setText(
                employeeSurvey.getSurvey().getSurveyName() + " (" +
                        employeeSurvey.getSurvey().getDate() + " - " +
                        employeeSurvey.getSurvey().getDateEnd() + ") "
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

        HorizontalLayout surveyLine = new HorizontalLayout();
        surveyLine.add(
                renderHeaderComponent("Опитування:", employeeSurvey.getSurvey().getSurveyName()),
                renderHeaderComponent("Дата початку:", employeeSurvey.getSurvey().getDate()),
                renderHeaderComponent("Дата завеершення:", employeeSurvey.getSurvey().getDateEnd())
        );
        //header.add(surveyLine);

        HorizontalLayout employeeLine = new HorizontalLayout();
        employeeLine.add(
                renderHeaderComponent("Співробітник:", employeeSurvey.getEmployee().getLastName() + " " +
                        employeeSurvey.getEmployee().getFirstName()),
                renderHeaderComponent("Посада:", employeeSurvey.getEmployee().getPosition().getPositionName()),
                renderHeaderComponent("Email:", employeeSurvey.getEmployee().getEmail())
        );
        //header.add(employeeLine);

        HorizontalLayout managerLine = new HorizontalLayout();
        managerLine.add(
                renderHeaderComponent("Менеджер:", employeeSurvey.getManager().getLastName() + " " +
                        employeeSurvey.getEmployee().getFirstName()),
                renderHeaderComponent("Посада:", employeeSurvey.getManager().getPosition().getPositionName()),
                renderHeaderComponent("Email:", employeeSurvey.getManager().getEmail())
        );
        //header.add(managerLine);
        return header;
    }

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
        Button submitButton = new Button("Зберегти", e -> {
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
                        Answers newAnswers = answersService.create(employeeSurveyId, es.getKey(), EMPLOYEE.name(), value);
                        answersMap.put(es.getKey(), newAnswers);
                    }
                    System.out.println("Saved");
                }
            });
        });
        submitButton.addClassNames(LumoUtility.Background.PRIMARY);
        submitButton.addClassNames(LumoUtility.TextColor.PRIMARY_CONTRAST);

        Button finishButton = new Button("Готово");
        finishButton.addClassNames(LumoUtility.Background.PRIMARY);
        finishButton.addClassNames(LumoUtility.TextColor.PRIMARY_CONTRAST);
        footer.add(submitButton, finishButton);
        return footer;
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

    private Component renderDropdownQuestion(SurveyQuestion question, Answers answers) {
        Select<String> dropdown = new Select<>();
        //Label label = new Label(question.getQuestion().getText());
        dropdown.setLabel(question.getQuestion().getText());
        dropdown.setItems("1", "2", "3", "4", "5");
        dropdown.setWidthFull();
        return dropdown;
    }

    private Component renderCheckBoxQuestion(SurveyQuestion question, Answers answers) {
        Checkbox checkbox = new Checkbox(question.getQuestion().getText());
        return checkbox;
    }

    private Component renderRadioButtonQuestion(SurveyQuestion question, Answers answers) {
        RadioButtonGroup<String> optionsGroup = new RadioButtonGroup<>();
//        label.addClassNames(
//                LumoUtility.TextColor.BODY,
//                LumoUtility.FontSize.SMALL
//        );
//        optionsGroup.getStyle().set("overflow-y", "auto");
//        optionsGroup.setLabel(question.getQuestion().getDescription());
        optionsGroup.setWidthFull();
        switch (question.getQuestion().getAnswers()) {
            case 2:
                optionsGroup.setItems("1", "2");
                optionsGroup.setRenderer(new TextRenderer<>((mn) -> switch (mn) {
                    case "1" -> "Так";
                    case "2" -> "Ні";
                    default -> "";
                }));
                break;
            case 3:
                optionsGroup.setItems("Так", "Частково", "Ні");
                break;
            case 4:
                optionsGroup.setItems("1", "2", "3", "4");
                optionsGroup.setRenderer(new TextRenderer<>((mn) -> switch (mn) {
                    case "1" -> "Відмінно";
                    case "2" -> "Добре";
                    case "3" -> "Задовільно";
                    case "4" -> "Не задовільно";
                    default -> "";
                }));
                break;
            case 5:
                optionsGroup.setItems("Відмінно", "Добре", "Задовільно", "Не задовільно", "Погано");
                break;
            default:
                List<String> list = new ArrayList<>();
                for (int i = 0; i < question.getQuestion().getAnswers(); i++) {
                    list.add(Integer.toString(i + 1));
                }
                optionsGroup.setItems(list);
                break;

        }
        if (answers != null) {
            optionsGroup.setValue(answers.getText());
        }
        //   optionsGroup.getStyle().set("flexDirection", "column"); // Розташувати по вертикалі
        return optionsGroup;
    }

    //    private void renderTextQuestion(SurveyQuestion question) {
//        TextField questionField = new TextField(question.getQuestion().getText());
//        questionField.setWidth("400px");
//        add(questionField);
//    }
    private Component renderTextQuestion(SurveyQuestion question, Answers answers) {
        TextField textField = new TextField();
        textField.setWidthFull();
        if (answers != null) {
            textField.setValue(answers.getText());
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

