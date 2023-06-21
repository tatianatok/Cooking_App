package pro.sky.cookingappnew.impl;

import pro.sky.cookingappnew.model.Recipe;
import pro.sky.cookingappnew.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {

        private final Map<Long, Recipe> recipes = new HashMap<>();
        private Long recipeId = 1L;
        @Override
        public Recipe addNewRecipe(Recipe recipe) {
            recipes.putIfAbsent(recipeId, recipe);
            recipeId++;
            return recipe;
        }

        @Override
        public Recipe getRecipe(Long recipeId) {
            return recipes.get(recipeId);
        }

        @Override
        public Map<Long, Recipe> getAllRecipe() {
            return recipes;
        }

        @Override
        public Recipe putRecipe(Long recipeId, Recipe recipe) {
            if (this.recipes.containsKey(recipeId)) {
                this.recipes.put(recipeId, recipe);
                return recipe;
            }
            return null;
        }

        @Override
        public boolean deleteRecipe(Long recipeId) {
            return recipes.remove(recipeId) != null;
        }
}