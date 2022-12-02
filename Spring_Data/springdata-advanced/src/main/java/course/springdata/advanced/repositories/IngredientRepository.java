package course.springdata.advanced.repositories;

import course.springdata.advanced.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByNameStartsWith(String letters);

    List<Ingredient> findByNameIn(String[] names);

    Ingredient findByName(String name);

    @Transactional
    int deleteAllByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Ingredient as i Set i.price = i.price + i.price * :percentage WHERE i.name IN :ingredient_names")
    int updatePriceOfIngredientsInLIst(@Param("ingredient_names") Iterable<String> ingredient_names,
                                       @Param("percentage") double percentage);
}
