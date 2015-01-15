package com.damo.trastome;

public class Prestec {
    private String data;
    private int idItem;
    private int idContacte;

    public Prestec (String data, int idItem, int idContacte) {
        this.data = data;
        this.idItem = idItem;
        this.idContacte = idContacte;
    }

    public String getData(){
        return data;
    }

    public int getIdItem(){
        return idItem;
    }
}
