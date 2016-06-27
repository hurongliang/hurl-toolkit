package com.hurl.toolkit.elk;

/**
 * Created by hurongliang on 16/6/25.
 */
public class FieldMapping {
    public static final String INDEX_ANALYZED = "analyzed";
    public static final String INDEX_NOT_ANALYZED = "not_analyzed";
    public static final String INDEX_NO = "no";
    public static final String TYPE_STRING = "string";
    public static final String TYPE_BYTE = "byte";
    public static final String TYPE_SHORT = "short";
    public static final String TYPE_INTEGER = "integer";
    public static final String TYPE_LONG = "long";
    public static final String TYPE_FLOAT = "float";
    public static final String TYPE_DOUBLE = "double";
    public static final String TYPE_BOOLEAN = "boolean";
    public static final String TYPE_DATE = "date";
    private String name;
    private String index;
    private boolean store = true;
    private String type;
    private boolean addRaw = false;
    private String format;

    public static FieldMapping dateField(String name){
        return valueField(name, TYPE_DATE, null);
    }
    public static FieldMapping dateFieldWithFormat(String name, String formatPattern){

        return valueField(name, TYPE_DATE, formatPattern);
    }
    public static FieldMapping booleanField(String name){
        return valueField(name, TYPE_BOOLEAN, null);
    }
    public static FieldMapping intField(String name){
        return valueField(name, TYPE_INTEGER, null);
    }
    public static FieldMapping longField(String name){
        return valueField(name, TYPE_LONG, null);
    }
    public static FieldMapping floatField(String name){
        return valueField(name, TYPE_FLOAT, null);
    }
    public static FieldMapping doubleField(String name){
        return valueField(name, TYPE_DOUBLE, null);
    }
    public static FieldMapping stringField(String name, boolean analyzed){
        return textField(name, false);
    }
    public static FieldMapping stringFieldWithRaw(String name, boolean analyzed){
        return textField(name, true);
    }
    private static FieldMapping valueField(String name, String type, String format){
        FieldMapping mapping = new FieldMapping();
        mapping.setName(name);
        mapping.setType(type);
        mapping.setIndex(INDEX_NOT_ANALYZED);
        mapping.setStore(true);
        mapping.setAddRaw(false);
        mapping.setFormat(format);
        return mapping;
    }
    private static FieldMapping textField(String name, boolean addRow){
        FieldMapping mapping = new FieldMapping();
        mapping.setName(name);
        mapping.setType(TYPE_STRING);
        mapping.setIndex(INDEX_ANALYZED);
        mapping.setStore(true);
        mapping.setAddRaw(addRow);
        return mapping;
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

    public boolean isStore() {
        return store;
    }

    public void setStore(boolean store) {
        this.store = store;
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
