package com.afrosimova.prmanager.views;

import com.afrosimova.prmanager.entities.Answers;
import com.afrosimova.prmanager.entities.SurveyQuestion;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionComponents {
    public static Map<Integer, List<String>> radioGroupValues = new HashMap<>();

    static {
        radioGroupValues.put(2, List.of("Так", "Ні"));
        radioGroupValues.put(3, List.of("Так", "Частково", "Ні"));
        radioGroupValues.put(4, List.of("Відмінно", "Добре", "Задовільно", "Не задовільно"));
        radioGroupValues.put(5, List.of("Відмінно", "Добре", "Задовільно", "Не задовільно", "Погано"));
    }

    public static List<String> getRadioGroupIds(int count) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            result.add("" + i);
        }
        return result;
    }

    public static List<String> getRadioGroupValues(int count) {
        var values = radioGroupValues.get(count);
        if (values != null) {
            return values;
        }
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            result.add("Оцінка " + i);
        }
        return result;
    }

    public static List<String> getDropDownValues(int count) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            result.add("Оцінка " + i);
        }
        return result;
    }
}
