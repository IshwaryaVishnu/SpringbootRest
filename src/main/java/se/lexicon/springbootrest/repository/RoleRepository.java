package se.lexicon.springbootrest.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.springbootrest.model.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role>findByName(String name);
    List<Role>findAllByOrderByDesc();
}
