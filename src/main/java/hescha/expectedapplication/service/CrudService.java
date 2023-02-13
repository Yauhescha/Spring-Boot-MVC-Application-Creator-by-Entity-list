package hescha.expectedapplication.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@RequiredArgsConstructor
public abstract class CrudService<Entity> {
    public final JpaRepository<Entity, Long> repository;

    public Entity create(Entity entity) {
        return repository.saveAndFlush(entity);
    }

    public Entity read(long id) {
        return repository.findById(id).get();
    }

    public List<Entity> readAll() {
        return repository.findAll();
    }

    public Entity update(Entity entity) {
        return repository.saveAndFlush(entity);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}