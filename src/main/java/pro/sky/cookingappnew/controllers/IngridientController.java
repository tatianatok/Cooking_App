package pro.sky.cookingappnew.controllers;

import pro.sky.cookingappnew.model.Ingridients;
import pro.sky.cookingappnew.services.IngridientsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/ingridient")

public class IngridientController {

    private final IngridientsService ingridientsService;

    public IngridientController(IngridientsService ingridientsService) {
        this.ingridientsService = ingridientsService;
    }

    @GetMapping("/addnewingridient")//Добавить новый ингредиент
    public Ingridients addNewIngridient(@RequestParam Ingridients ingridient) {
        ingridientsService.addNewIngridient(ingridient);
        return ingridient;
    }

    @GetMapping("/getingridient")//Получить ингредиент по его идентификатору
    public Ingridients getIngridient(@RequestParam Long idIngridient) {
        return ingridientsService.getIngridient(idIngridient);
    }

    @GetMapping//Получить все ингредиенты
    public ResponseEntity<Map<Long, Ingridients>> getAllIngridient() {
        Map<Long, Ingridients> ingridients = ingridientsService.getAllIngridient();
        if (ingridients == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingridients);
    }

    @PutMapping("/{idIngridient}")//Изменить ингредиент
    public ResponseEntity<Ingridients> putIngridient(@PathVariable Long idIngridient, @RequestBody Ingridients ingridient) {
        Ingridients ingridient1 = ingridientsService.putIngridient(idIngridient, ingridient);
        if (ingridient1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingridient);
    }

    @DeleteMapping("/{idIngridiet}")//Удалить ингредиент
    public ResponseEntity<Void> deleteIngridient(@PathVariable Long idIngridient) {
        if (ingridientsService.deleteIngridient(idIngridient)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
