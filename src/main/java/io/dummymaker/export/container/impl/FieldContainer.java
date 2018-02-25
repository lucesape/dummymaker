package io.dummymaker.export.container.impl;

import java.lang.reflect.Field;

/**
 * Used by BasicClassContainer to contain field value, and final field name
 *
 * @see BasicClassContainer
 *
 * @author GoodforGod
 * @since 03.09.2017
 */
public class FieldContainer {

    /**
     * Class Field
     */
    private final Field field;

    /**
     * Final field name (renamed or converted by naming strategy)
     */
    private final String exportName;

    public FieldContainer(Field field, String finalFieldName) {
        this.field = field;
        this.exportName = finalFieldName;
    }

    public Field getField() {
        return field;
    }

    public String getExportName() {
        return exportName;
    }
}
