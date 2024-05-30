package com.afrosimova.prmanager.views.admin.forms;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SqlTimestampToLocalDateTimeConverter implements Converter<LocalDateTime, Timestamp> {
    @Override
    public Result<Timestamp> convertToModel(LocalDateTime value,
                                       ValueContext context) {
        if (value == null) {
            return Result.ok(null);
        }
        return Result.ok(Timestamp.valueOf(value));
    }

    @Override
    public LocalDateTime convertToPresentation(java.sql.Timestamp value,
                                               ValueContext context) {
        return value != null ? value.toLocalDateTime() : null;
    }
}