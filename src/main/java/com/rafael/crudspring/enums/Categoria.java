package com.rafael.crudspring.enums;

public enum Categoria {
    
    BACK_END("Back-end"), FRONT_END("Front-end");

    private String value;

    private Categoria(String value){
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
