package com.afrosimova.prmanager.views.admin.forms;

import com.afrosimova.prmanager.entities.Question;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TextRenderer;

public class QuestionForm extends GeneralForm<Question> {
    TextField text = new TextField("Питання");
    TextField description = new TextField("Опис");
    Select<Integer> type = new Select<>("Тип відповіді", l -> {
    });
    Select<Integer> answers = new Select<>("Кількість варіантів", l -> {
    });

    public QuestionForm() {
        super(Question.class);
        addClassName("question-form");
        type.setItems(1, 2, 3, 4);
        type.setRenderer(new TextRenderer<>((mn) -> {
            switch (mn) {
                case 1:
                    return "Текст";
                case 2:
                    return "Чекбокс";
                case 3:
                    return "Вибір";
                case 4:
                    return "Список";
            }
            return "";
        }));
        answers.setItems(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        customBinder();
        binder.bindInstanceFields(this);
        add(text, description, type, answers, createButtonsLayout());
    }

    @Override
    protected void customBinder() {
        binder.forField(type).bind(Question::getType, Question::setType);
        binder.forField(answers).bind(Question::getAnswers, Question::setAnswers);

//        binder.forField(date)
//                .withConverter(new SqlTimestampToLocalDateTimeConverter())
//                .bind(Survey::getDate, Survey::setDate);
//        binder.forField(dateEnd)
//                .withConverter(new SqlTimestampToLocalDateTimeConverter())
//                .bind(Survey::getDateEnd, Survey::setDateEnd);
    }

    @Override
    protected void initComponents() {
        add(text, description, type, answers);
    }
}

