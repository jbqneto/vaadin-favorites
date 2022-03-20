package io.jbqneto.favorites.infrastructure.repository.port;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CRUDRepository<T> extends JpaRepository<T, Long> {
    void delete(T entity);
    T retrieve(long id);
    List<T> list();
}
