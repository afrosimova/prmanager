package com.afrosimova.prmanager.views.admin.forms;

import com.afrosimova.prmanager.entities.Survey;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.textfield.TextField;

public class SurveyForm extends GeneralForm<Survey> {
    TextField surveyName = new TextField("Назва оцінювання");
    DateTimePicker date = new DateTimePicker("Дата початку");
    DateTimePicker dateEnd = new DateTimePicker("Дата завершення");

    public SurveyForm() {
        super(Survey.class);
        addClassName("survey-form");
        customBinder();
        binder.bindInstanceFields(this);
        add(surveyName, date, dateEnd, createButtonsLayout());
    }

    @Override
    protected void customBinder() {
        binder.forField(date)
                .withConverter(new SqlTimestampToLocalDateTimeConverter())
                .bind(Survey::getDate, Survey::setDate);
        binder.forField(dateEnd)
                .withConverter(new SqlTimestampToLocalDateTimeConverter())
                .bind(Survey::getDateEnd, Survey::setDateEnd);
    }

    @Override
    protected void initComponents() {
        add(surveyName, date, dateEnd);
    }
}
