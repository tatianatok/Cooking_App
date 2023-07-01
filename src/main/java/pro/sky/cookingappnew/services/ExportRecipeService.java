package pro.sky.cookingappnew.services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import pro.sky.cookingappnew.impl.FileServiceImpl;
import pro.sky.cookingappnew.model.Ingridients;
import pro.sky.cookingappnew.model.Recipe;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ExportRecipeService {

    private final static String STORE_FILE_NAME = "recipes";
    private int idCounter = 0;

    private Map<Integer, Recipe> recipes = new HashMap<>();

    private final IngridientsService ingredientsService;

    private final FileServiceImpl fileServiceImpl;
    private ObjectMapper objectMapper;

    public ExportRecipeService(IngridientsService ingredientsService, FileServiceImpl fileServiceImpl) {
        this.ingredientsService = ingredientsService;
        this.fileServiceImpl = fileServiceImpl;
        Map<Integer, Recipe> storedMap = fileServiceImpl.readFromFile(STORE_FILE_NAME, new TypeReference<>() {
        });
        this.recipes = Objects.requireNonNullElseGet(storedMap, HashMap::new);
    }

    public void exportAllRecipes (PrintWriter writer) throws IOException {
        objectMapper.writeValue (writer, this.recipes.values());
    }
   
}


