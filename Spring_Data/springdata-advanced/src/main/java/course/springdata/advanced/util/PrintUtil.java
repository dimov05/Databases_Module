package course.springdata.advanced.util;

import course.springdata.advanced.entities.Ingredient;
import course.springdata.advanced.entities.Shampoo;

public class PrintUtil {
    public static void printShampoo(Shampoo s) {
        System.out.printf("|%5d | %-20.20s | %-8.8s | %8.2f | %-40.40s |%n",
                s.getId(), s.getBrand(), s.getSize(), s.getPrice()
                , s.getLabel().getTitle() + " - " + s.getLabel().getSubtitle());
    }

    public static void printIngredientName(Ingredient ingredient) {
        System.out.printf("%s%n",
                ingredient.getName());
    }
    public static void printIngredient(Ingredient ingredient){
        System.out.printf("|%5d | %-20.20s | %8.2f |%n",
                ingredient.getId(), ingredient.getName(), ingredient.getPrice());
    }

    public static void printShampooName(Shampoo shampoo) {
        System.out.printf("%s%n", shampoo.getBrand());
    }
}
