package com.hurl.toolkit.elk;

/**
 * Created by hurongliang on 16/6/25.
 */
public class FieldMapping {
    public static final String INDEX_ANALYZED = "analyzed";
    public static final String INDEX_NOT_ANALYZED = "not_analyzed";
    public static final String TYPE_STRING = "string";
    public static final String TYPE_INTEGER = "integer";
    public static final String TYPE_LONG = "long";
    public static final String TYPE_FLOAT = "float";
    public static final String TYPE_DOUBLE = "double";
    public static final String TYPE_BOOLEAN = "boolean";
    public static final String TYPE_DATE = "date";
    private String name;
    private String index;
    private String type;
    private boolean addRaw = false;
    private String format;

    public static FieldMapping dateField(String name){
        return dateField(name, null);
    }
    public static FieldMapping dateField(String name, String format){
        FieldMapping mapping = new FieldMapping();
        mapping.setName(name);
        mapping.setType(TYPE_DATE);
        mapping.setFormat(format);
        return mapping;
    }
    public static FieldMapping stringField(String name){
        return stringField(name, true, false);
    }
    public static FieldMapping stringFieldWithRaw(String name, boolean analyzed){
        return stringField(name, analyzed, true);
    }
    public static FieldMapping stringFieldNotAnalyzed(String name){
        return stringField(name, false, false);
    }
    private static FieldMapping stringField(String name, boolean analyzed, boolean addRaw){
        FieldMapping mapping = new FieldMapping();
        mapping.setName(name);
        mapping.setType(TYPE_STRING);
        if(analyzed){
            mapping.setIndex(INDEX_ANALYZED);
            mapping.setAddRaw(addRaw);
        } else {
            mapping.setIndex(INDEX_NOT_ANALYZED);
        }
        return mapping;
    }
    public static FieldMapping booleanField(String name){
        FieldMapping mapping = new FieldMapping();
        mapping.setName(name);
        mapping.setType(TYPE_BOOLEAN);
        return mapping;
    }
    public static FieldMapping floatField(String name){
        return numberField(name, TYPE_FLOAT);
    }
    public static FieldMapping doubleField(String name){
        return numberField(name, TYPE_DOUBLE);
    }
    public static FieldMapping intField(String name) {
        return numberField(name, TYPE_INTEGER);
    }
    public static FieldMapping longField(String name) {
        return numberField(name, TYPE_LONG);
    }
    private static FieldMapping numberField(String name, String type){
        FieldMapping m = new FieldMapping();
        m.setName(name);
        m.setType(type);
        return m;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAddRaw() {
        return addRaw;
    }

    public void setAddRaw(boolean addRaw) {
        this.addRaw = addRaw;
    }
}
