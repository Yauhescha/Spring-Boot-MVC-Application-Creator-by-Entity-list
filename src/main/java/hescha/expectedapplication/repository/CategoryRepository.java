package hescha.expectedapplication.repository;

import hescha.expectedapplication.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

    Category findByNameIgnoreCase(String name);

    List<Category> findByNameContains(String name);

    List<Category> findByCategoriesContains(Category category);
}
