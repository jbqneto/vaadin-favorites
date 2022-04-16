package io.jbqneto.favorites.application.domain.service;


import com.vaadin.flow.router.NotFoundException;
import io.jbqneto.favorites.application.domain.model.business.IDModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CRUDService<E, M extends IDModel> {

    protected final JpaRepository<E, Long> repository;

    public CRUDService(JpaRepository repository) {
        this.repository = repository;
    }

    public M create(@NotNull M model) {
        var entity = mapFromDomain(model);

        return mapFromEntity(repository.save(entity));
    }

    public M edit(@NotNull M model) {
        var exists = repository.findById(model.getId());

        if (exists.isEmpty())
            throw new NotFoundException("Model not found: " + model.getId());

        var entity = mapFromDomain(model);

        return mapFromEntity(repository.save(entity));
    }

    public M retrieve(long id) {
        var entity = repository.findById(id);

        if (entity.isEmpty())
            throw new NotFoundException("Not not found: " + id);

        return mapFromEntity(entity.get());
    }

    public List<M> list() {
        var list = repository.findAll();

        return list.stream().map(this::mapFromEntity).collect(Collectors.toList());
    }

    public void delete(M model) {
        this.repository.deleteById(model.getId());
    }

    public abstract M mapFromEntity(E entity);
    public abstract E mapFromDomain(M model);
}
