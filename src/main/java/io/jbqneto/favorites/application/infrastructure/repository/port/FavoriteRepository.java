package io.jbqneto.favorites.application.infrastructure.repository.port;

import io.jbqneto.favorites.application.infrastructure.entity.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {

    @Query("SELECT C FROM FavoriteEntity C " +
            " WHERE lower(C.title) like concat('%', :search, '%') " +
            " or lower(C.url) like concat('%s', :search, '%')")
    List<FavoriteEntity> search(String search);
}
