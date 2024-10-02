package dev.agitrubard.datetime.service;

import dev.agitrubard.datetime.model.request.EventCreateRequest;
import dev.agitrubard.datetime.model.response.EventResponse;

import java.util.List;

public interface EventService {

    List<EventResponse> findAll();

    EventResponse findById(Long id);

    void create(EventCreateRequest createRequest);

}
