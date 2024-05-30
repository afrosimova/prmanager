package com.afrosimova.prmanager.views.admin.forms;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

public abstract class GeneralForm<T> extends FormLayout {
    Class<T> clazz;
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    Binder<T> binder;

    public GeneralForm(Class<T> clazz) {
        this.clazz = clazz;
        binder = new BeanValidationBinder<>(clazz);
    }

    protected abstract void customBinder();

    protected abstract void initComponents();

    protected Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave()); // <1>
        delete.addClickListener(event -> fireEvent(new GeneralForm.DeleteEvent(this, binder.getBean()))); // <2>
        close.addClickListener(event -> fireEvent(new GeneralForm.CloseEvent(this))); // <3>

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid())); // <4>
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new GeneralForm.SaveEvent(this, binder.getBean())); // <6>
        }
    }

    public void setEntity(T entity) {
        binder.setBean(entity); // <1>
    }

    // Events
    public static abstract class EntityFormEvent extends ComponentEvent<GeneralForm> {
        private final Object entity;

        protected EntityFormEvent(GeneralForm source, Object entity) {
            super(source, false);
            this.entity = entity;
        }

        public <T> T getEntity() {
            return (T) entity;
        }
    }

    public static class SaveEvent extends GeneralForm.EntityFormEvent {
        <T> SaveEvent(GeneralForm<T> source, Object entity) {
            super(source, entity);
        }
    }

    public static class DeleteEvent extends GeneralForm.EntityFormEvent {
        <T> DeleteEvent(GeneralForm<T> source, T entity) {
            super(source, entity);
        }

    }

    public static class CloseEvent extends GeneralForm.EntityFormEvent {
        <T> CloseEvent(GeneralForm<T> source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<GeneralForm.DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<GeneralForm.SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<GeneralForm.CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}