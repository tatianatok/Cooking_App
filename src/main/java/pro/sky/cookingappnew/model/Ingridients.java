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

    public String getTitleIngridient() {
        return titleIngridient;
    }

    public void setTitleIngridient(String titleIngridient) {
        this.titleIngridient = titleIngridient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}