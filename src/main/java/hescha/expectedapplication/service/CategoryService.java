package main.java.hescha.expectedapplication.service;

import main.java.hescha.expectedapplication.model.Category;
import main.java.hescha.expectedapplication.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends CrudService<Category> {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Category findByName(String name) {
        return repository.findByName(name);
    }

    public Category findByNameIgnoreCase(String name) {
        return repository.findByNameIgnoreCase(name);
    }

    public List<Category> findByNameContains(String name) {
        return repository.findByNameContains(name);
    }

    public List<Category> findByCategoriesContains(Category category) {
        return repository.findByCategoriesContains(category);
    }

    public Category update(Long id, Category entity) {
        Category read = read(id);
        if(read == null){
            throw new RuntimeException("Entity not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(Category entity, Category read) {
        read.setName(entity.getName());
        read.setCategories(entity.getCategories());
    }
}
