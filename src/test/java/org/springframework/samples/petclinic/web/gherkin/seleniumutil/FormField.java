package org.springframework.samples.petclinic.web.gherkin.seleniumutil;

/**
 * Created by michael on 05/12/15.
 */
public class FormField {

    private String field;
    private String value;

    public FormField(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "{" +
                "field='" + field + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
