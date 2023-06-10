package pro.sky.cookingappnew.impl;

import pro.sky.cookingappnew.model.Ingridients;
import pro.sky.cookingappnew.services.IngridientsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EngridientsServiceImpl implements IngridientsService {

    private final Map<Long, Ingridients> ingridient = new HashMap<>();
    private Long idIngridient = 1L;

    @Override
    public Ingridients addNewIngridient(Ingridients ingridients) {
        ingridient.put(idIngridient, ingridients);
        idIngridient++;
        return ingridients;
    }

    @Override
    public Ingridients getIngridient(Long idIngridient) {
        return ingridient.get(idIngridient);
    }

    @Override
    public Map<Long, Ingridients> getAllIngridient() {
        return ingridient;
    }

    @Override
    public Ingridients putIngridient(Long idIngridient, Ingridients ingridient) {
        return ingridient;
    }

    @Override
    public boolean deleteIngridient(Long idIngridient) {
        return ingridient.remove(idIngridient) != null;
    }
}

