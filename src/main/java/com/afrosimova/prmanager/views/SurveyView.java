package com.afrosimova.prmanager.views;

import com.afrosimova.prmanager.MainContentLayout;
import com.afrosimova.prmanager.entities.SurveyQuestion;
import com.afrosimova.prmanager.services.SurveyService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.Comparator;
import java.util.List;

@Route(value = "survey", layout = MainContentLayout.class)
@RolesAllowed("USER")
public class SurveyView extends VerticalLayout implements HasUrlParameter<String> {
    private long employeeSurveyId;
    //TextField questionText1 = new TextField("blabla");

    //private TextField questionField;
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
        List<SurveyQuestion> questions = surveyService.findPositionQuestionBy(employeeSurveyId);
        questions.sort(Comparator.comparing(SurveyQuestion::getQuestionOrder));
        for (SurveyQuestion question : questions) {
            switch (question.getQuestion().getType()) {
                case 1 -> renderTextQuestion(question);
                case 2 -> renderCheckBoxQuestion(question);
                case 3 -> renderRadioButtonQuestion(question);
                case 4 -> renderDropdownQuestion(question);
            }
            renderQuestion(question);
        }
        // Кнопка для підтвердження відповіді
        Button submitButton = new Button("Зберегти", e -> {
//            String question = questionField.getValue();
//            String selectedOption = optionsGroup.getValue();
//            System.out.println("Питання: " + question);
//            System.out.println("Обраний варіант відповіді: " + selectedOption);
            // Тут можна додати логіку обробки відповіді
        });
        add(submitButton);
    }

    private void renderDropdownQuestion(SurveyQuestion question) {
        Select<String> dropdown = new Select<>();
        dropdown.setLabel(question.getQuestion().getText());
        dropdown.setItems("Варіант 1", "Варіант 2", "Варіант 3", "Варіант 4", "Варіант 5");
        add(dropdown);
    }

    private void renderCheckBoxQuestion(SurveyQuestion question) {
        Checkbox checkbox = new Checkbox(question.getQuestion().getText());
        add(checkbox);
    }

    private void renderRadioButtonQuestion(SurveyQuestion question) {
        RadioButtonGroup<String> optionsGroup = new RadioButtonGroup<>();
        optionsGroup.setLabel(question.getQuestion().getText());
        optionsGroup.setItems("Варіант 1", "Варіант 2", "Варіант 3", "Варіант 4");
        optionsGroup.getStyle().set("flexDirection", "column"); // Розташувати по вертикалі
        add(optionsGroup);
    }

    private void renderTextQuestion(SurveyQuestion question) {
        TextField questionField = new TextField(question.getQuestion().getText());
        questionField.setWidth("400px");
        add(questionField);
    }

    private void renderQuestion(SurveyQuestion question) {
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

