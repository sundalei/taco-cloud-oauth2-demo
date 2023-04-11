package tacos.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.data.IngredientRepository;
import tacos.domain.Ingredient;

@Component
public record IngredientByIdConverter(
        IngredientRepository ingredientRepository) implements Converter<String, Ingredient> {

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }

}
