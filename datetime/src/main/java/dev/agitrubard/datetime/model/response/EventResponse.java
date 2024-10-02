package dev.agitrubard.datetime.model.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventResponse {

    private Long id;
    private String name;
    private LocalDate date;
    private LocalTime time;
    private LocalDateTime publishAt;
    private boolean isPublished;
    private LocalDateTime createdAt;

    public EventResponse(Long id,
                         String name,
                         LocalDate date,
                         LocalTime time,
                         LocalDateTime publishAt,
                         boolean isPublished,
                         LocalDateTime createdAt) {

        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.publishAt = publishAt;
        this.isPublished = isPublished;
        this.createdAt = createdAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDateTime getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(LocalDateTime publishAt) {
        this.publishAt = publishAt;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
