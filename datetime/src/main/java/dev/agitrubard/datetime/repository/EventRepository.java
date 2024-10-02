package dev.agitrubard.datetime.repository;

import dev.agitrubard.datetime.model.entity.EventEntity;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    List<EventEntity> findAll();

    Optional<EventEntity> findById(Long id);

    void save(EventEntity eventEntity);

}
