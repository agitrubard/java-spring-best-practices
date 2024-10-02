package dev.agitrubard.datetime.model.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventCreateRequest {

    private String name;
    private LocalDate date;
    private LocalTime time;
    private LocalDateTime publishAt;

    public EventCreateRequest() {
    }

    public EventCreateRequest(String name, LocalDate date, LocalTime time, LocalDateTime publishAt) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.publishAt = publishAt;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public LocalDateTime getPublishAt() {
        return publishAt;
    }

    @Override
    public String toString() {

        if (publishAt == null) {
            return "{" +
                    "\"name\": \"" + name + "\"," +
                    "\"date\": \"" + date + "\"," +
                    "\"time\": \"" + time + "\"" +
                    "}";
        }

        return "{" +
                "\"name\": \"" + name + "\"," +
                "\"date\": \"" + date + "\"," +
                "\"time\": \"" + time + "\"," +
                "\"publishAt\": \"" + publishAt + "\"" +
                "}";
    }
}
