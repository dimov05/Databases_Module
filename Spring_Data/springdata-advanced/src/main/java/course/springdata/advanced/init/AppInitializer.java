package course.springdata.advanced.init;

import course.springdata.advanced.entities.Ingredient;
import course.springdata.advanced.entities.Shampoo;
import course.springdata.advanced.repositories.IngredientRepository;
import course.springdata.advanced.repositories.LabelRepository;
import course.springdata.advanced.repositories.ShampooRepository;
import course.springdata.advanced.util.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static course.springdata.advanced.entities.Size.MEDIUM;
import static course.springdata.advanced.util.PrintUtil.printShampoo;

@Component
public class AppInitializer implements CommandLineRunner {
    private final ShampooRepository shampooRepo;
    private final IngredientRepository ingredientRepo;
    private final LabelRepository labelRepo;

    @Autowired
    public AppInitializer(ShampooRepository shampooRepo, IngredientRepository ingredientRepo, LabelRepository labelRepo) {
        this.shampooRepo = shampooRepo;
        this.ingredientRepo = ingredientRepo;
        this.labelRepo = labelRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        /*Ex. 1 Select shampoos by size
        shampooRepo.findBySizeOrderById(MEDIUM)
                .forEach(PrintUtil::printShampoo);*/

        /*Ex. 2 Select shampoos by Size or label Id and order by Price Asc
        shampooRepo.findBySizeOrLabelIdOrderByPriceAsc(MEDIUM, (long) 10)
                .forEach(PrintUtil::printShampoo);*/

        /*Ex. 3 Select Shampoos by Price
        shampooRepo.findByPriceGreaterThanOrderByPriceDesc(5)
                .forEach(PrintUtil::printShampoo);*/

        /*Ex. 4 Select Ingredient by Name
        ingredientRepo.findByNameStartsWith("M")
                .forEach(PrintUtil::printIngredientName);*/

        /*Ex. 5 Select Ingredients by Names
        ingredientRepo.findByNameIn(new String[]{"Lavender", "Herbs", "Apple"})
                .forEach(PrintUtil::printIngredientName);*/

        /*Ex. 6 Count Shampoos By Price
        System.out.println(shampooRepo.countByPriceLessThan(8.50));*/

        /*Ex. 7 Select Shampoos by Ingredients
        shampooRepo.findWithIngredientsIn(new HashSet<>(Arrays.asList("Berry", "Mineral-Collagen")))
                .forEach(PrintUtil::printShampooName);*/

        /*Ex. 8 Select Shampoos by Ingredients Count
        shampooRepo.findWithIngredientsLessThan(2)
                .forEach(PrintUtil::printShampooName);*/

        /*Ex. 9 Delete ingredients by Name
        String ingredientNameToDelete = "Cherry";
        Ingredient ingredientToDelete = ingredientRepo.findByName(ingredientNameToDelete);
        shampooRepo.findByIngredient(ingredientToDelete)
                .forEach(shampoo -> {
                    shampoo.getIngredients().remove(ingredientToDelete);
                });
        System.out.println(ingredientRepo.deleteAllByName(ingredientNameToDelete));*/

        /*Ex. 10-11 Update Ingredient price with percentage by Names
        ingredientRepo.findAll().forEach(PrintUtil::printIngredient);
        System.out.println("-".repeat(80) + "\n");

        System.out.printf("Ingredients updated: %d%n",
                ingredientRepo.updatePriceOfIngredientsInLIst(Set.of("Lavender", "Herbs", "Apple"), 8.5/100));

        ingredientRepo.findAll().forEach(PrintUtil::printIngredient);
        System.out.println("-".repeat(80) + "\n");*/

    }

}
