package io.jbqneto.favorites.domain.service;

import io.jbqneto.favorites.infrastructure.repository.port.FavoriteRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    private final FavoriteRepository repository;

    public FavoriteService(FavoriteRepository repository) {
        this.repository = repository;
    }


}
