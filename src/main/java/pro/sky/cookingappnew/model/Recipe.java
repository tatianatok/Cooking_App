package pro.sky.cookingappnew.model;

import lombok.Data;

import java.util.List;

@Data
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
}

