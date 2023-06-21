package pro.sky.cookingappnew.model;

import lombok.Data;


@Data
public class Ingridients {

    private String titleIngridient;
    private int quantity;
    private String measurement;

    public Ingridients(String titleIngridient, int quantity, String measurement) {
        this.titleIngridient = titleIngridient;
        this.quantity = quantity;
        this.measurement = measurement;
    }
}