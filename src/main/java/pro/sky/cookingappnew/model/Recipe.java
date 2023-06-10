package pro.sky.cookingappnew.model;

import java.util.List;

public class Recipe {

    private String recipeTitle;
    private int time;
    private List<Ingridients> ingridients;
    private List<String> preparation;

    public Recipe(String recipeTitle, int time, List<Ingridients> ingridients, List<String> preparation) {
        this.recipeTitle = recipeTitle;
        this.time = time;
        this.ingridients = ingridients;
        this.preparation = preparation;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<Ingridients> getIngridients() {
        return ingridients;
    }

    public void setIngredient(List<Ingridients> ingridients) {
        this.ingridients = ingridients;
    }

    public List<String> getPreparation() {
        return preparation;
    }

    public void setPreparation(List<String> preparation) {
        this.preparation = preparation;
    }
}

