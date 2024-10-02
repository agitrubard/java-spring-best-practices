package dev.agitrubard.datetime.service.impl;

import dev.agitrubard.datetime.model.entity.EventEntity;
import dev.agitrubard.datetime.model.request.EventCreateRequest;
import dev.agitrubard.datetime.model.response.EventResponse;
import dev.agitrubard.datetime.repository.EventRepository;
import dev.agitrubard.datetime.service.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<EventResponse> findAll() {
        List<EventEntity> eventEntities = eventRepository.findAll();
        return eventEntities.stream()
                .map(eventEntity -> new EventResponse(
                                eventEntity.getId(),
                                eventEntity.getName(),
                                eventEntity.getDate(),
                                eventEntity.getTime(),
                                eventEntity.getPublishAt(),
                                eventEntity.isPublished(),
                                eventEntity.getCreatedAt()
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    public EventResponse findById(Long id) {

        EventEntity eventEntity = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        return new EventResponse(
                eventEntity.getId(),
                eventEntity.getName(),
                eventEntity.getDate(),
                eventEntity.getTime(),
                eventEntity.getPublishAt(),
                eventEntity.isPublished(),
                eventEntity.getCreatedAt()
        );
    }

    @Override
    public void create(EventCreateRequest createRequest) {

        boolean isPublished = Optional.ofNullable(createRequest.getPublishAt())
                .map(publishAt -> publishAt.isBefore(LocalDateTime.now()))
                .orElse(true);

        EventEntity eventEntity = new EventEntity(
                createRequest.getName(),
                createRequest.getDate(),
                createRequest.getTime(),
                createRequest.getPublishAt(),
                isPublished
        );

        eventRepository.save(eventEntity);
    }

}
