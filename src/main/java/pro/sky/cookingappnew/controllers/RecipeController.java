package pro.sky.cookingappnew.controllers;

import pro.sky.cookingappnew.model.Recipe;
import pro.sky.cookingappnew.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe/")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/addnewrecipe")//Добавить новый рецепт
    public Recipe addNewRecipe(@RequestParam Recipe recipe) {
        recipeService.addNewRecipe(recipe);
        return recipe;
    }

    @GetMapping("/getrecipe")//Получить рецепт по ID
    public Recipe getRecipe(@RequestParam Long recipeId) {
        return recipeService.getRecipe(recipeId);
    }

    @GetMapping//Получить все рецепты
    public ResponseEntity<Map<Long, Recipe>> getAllRecipe()  {
        Map<Long, Recipe> recipe = recipeService.getAllRecipe();
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/{recipeId}")//Изменить рецепт
    public ResponseEntity<Recipe> putRecipe(@PathVariable Long recipeId, @RequestBody Recipe recipe) {
        Recipe recipe1 = recipeService.putRecipe(recipeId, recipe);
        if (recipe1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{recipeId}")//Удалить рецепт
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long recipeId) {
        if (recipeService.deleteRecipe(recipeId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
