package com.rafael.crudspring.enums;

public enum Status {
    ATIVO("active"), INATIVO("inactive");

    private String value;

    private Status(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }

    public String getValue() {
        return value;
    }
}
