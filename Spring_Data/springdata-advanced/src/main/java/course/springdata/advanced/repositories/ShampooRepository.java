package course.springdata.advanced.repositories;

import course.springdata.advanced.entities.Ingredient;
import course.springdata.advanced.entities.Label;
import course.springdata.advanced.entities.Shampoo;
import course.springdata.advanced.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findBySizeOrderById(Size size);

    List<Shampoo> findBySizeOrLabelIdOrderByPriceAsc(Size size, Long labelId);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(double price);

    int countByPriceLessThan(double price);

    @Query("SELECT s FROM Shampoo as s JOIN s.ingredients as i WHERE i = :ingredient")
    List<Shampoo> findByIngredient(@Param("ingredient") Ingredient ingredient);

    @Query("SELECT s FROM Shampoo as s JOIN s.ingredients as i WHERE i.name IN :ingredients_names")
    List<Shampoo> findWithIngredientsIn(@Param("ingredients_names") Set<String> ingredients_names);

    @Query("SELECT s FROM Shampoo as s WHERE s.ingredients.size < :ingredients_count")
    List<Shampoo> findWithIngredientsLessThan(@Param("ingredients_count") int ingredientsCount);


}
