package dev.agitrubard.datetime.controller;

import dev.agitrubard.datetime.model.request.EventCreateRequest;
import dev.agitrubard.datetime.model.response.EventResponse;
import dev.agitrubard.datetime.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    ResponseEntity<List<EventResponse>> findAll() {
        List<EventResponse> eventResponse = eventService.findAll();
        return ResponseEntity.ok(eventResponse);
    }

    @GetMapping("/event/{id}")
    ResponseEntity<EventResponse> findById(@PathVariable Long id) {
        EventResponse eventResponse = eventService.findById(id);
        return ResponseEntity.ok(eventResponse);
    }

    @PostMapping("/event")
    ResponseEntity<Void> create(@RequestBody EventCreateRequest createRequest) {
        eventService.create(createRequest);
        return ResponseEntity.ok().build();
    }

}
