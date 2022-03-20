package io.jbqneto.favorites.domain.service;


import io.jbqneto.favorites.infrastructure.repository.port.CRUDRepository;

import java.util.List;
import java.util.stream.Collectors;

public abstract class CRUDService<E, M> {

    private final CRUDRepository<E> repository;

    public CRUDService(CRUDRepository repository) {
        this.repository = repository;
    }

    public M save(M model) {
        var entity = mapFromDomain(model);

        return mapFromEntity(repository.save(entity));
    }

    public List<M> list() {
        var list = repository.findAll();

        return list.stream().map(this::mapFromEntity).collect(Collectors.toList());
    }

    public abstract M mapFromEntity(E entity);
    public abstract E mapFromDomain(M model);
}
