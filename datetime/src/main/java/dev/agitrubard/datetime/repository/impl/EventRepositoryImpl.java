package dev.agitrubard.datetime.repository.impl;

import dev.agitrubard.datetime.model.entity.EventEntity;
import dev.agitrubard.datetime.repository.EventRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
class EventRepositoryImpl implements EventRepository {

    private final List<EventEntity> events = new ArrayList<>();

    public List<EventEntity> findAll() {
        return events;
    }

    public Optional<EventEntity> findById(Long id) {
        return events.stream()
                .filter(eventEntity -> eventEntity.getId().equals(id))
                .findFirst();
    }

    public void save(EventEntity eventEntity) {
        eventEntity.setId(events.size() + 1L);
        eventEntity.setCreatedAt(LocalDateTime.now());
        events.add(eventEntity);
    }

}
