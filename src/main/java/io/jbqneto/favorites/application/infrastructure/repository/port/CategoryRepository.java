package io.jbqneto.favorites.application.infrastructure.repository.port;

import io.jbqneto.favorites.application.infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
