package pro.sky.cookingappnew.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.cookingappnew.model.Ingridients;
import pro.sky.cookingappnew.model.Recipe;
import pro.sky.cookingappnew.services.FileService;
import pro.sky.cookingappnew.services.RecipeService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/files")
public class FileController {

    private FileService fileService;
    private RecipeService recipeService;

    public FileController(FileService fileService, RecipeService recipeService) {
        this.fileService = fileService;
        this.recipeService = recipeService;
    }

    @GetMapping("/exportRec")
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        File file = fileService.getDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipeLog.json\"")
                    .body(resource);

        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/importRec", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) {
        fileService.cleanDataFile();
        File dataFile = fileService.getDataFile();

        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/importIng", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile1(@RequestParam MultipartFile file) {
        fileService.cleanDataFile1();
        File dataFile = fileService.getDataFile1();

        try (FileOutputStream fos1 = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos1);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    //Получение всех рецептов из приложения
    @Operation(
            summary = "Загружаем список рецептов в формате txt"
    )
    @GetMapping("/exportAllRecipes")
    public void exportAllRecipes(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=sample.txt");
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8));
        for (Recipe recipe : recipeService.getAllRecipe().values()) {
            writer.println(recipe.getRecipeTitle());
            writer.println("Время приготовления: %d минут".formatted(recipe.getTime()));
            writer.println("Ингредиенты:");
            for (Ingridients ingredients : recipe.getIngridients()) {
                writer.println("\t%s - %d %s".formatted(ingredients.getTitleIngridient(), ingredients.getQuantity(), ingredients.getMeasurement()));
            }
            writer.println("Инструкция приготовления");
            for (int i = 0; i < recipe.getPreparation().size(); i++) {
                writer.println("%d. %s".formatted(i + 1, recipe.getPreparation().get(i)));
            }
            writer.println(" ");
        }
        writer.flush();
    }

}






// public void exportAllRecipes(HttpServletResponse response) throws IOException {
//        ContentDisposition disposition = ContentDisposition.attachment()
//                .name("recipe.txt")
//                .build();
//        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, disposition.toString());
//        exportRecipeService.exportAllRecipes(response.getWriter());