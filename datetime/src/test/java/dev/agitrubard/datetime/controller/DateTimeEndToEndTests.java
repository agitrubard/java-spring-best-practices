package dev.agitrubard.datetime.controller;

import dev.agitrubard.datetime.model.entity.EventEntity;
import dev.agitrubard.datetime.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class DateTimeEndToEndTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventRepository eventRepository;

    @Test
    void givenEventCreateRequestWithPastPublishAt_whenEventCreatedWithIsPublishedTrue_thenReturnOk() throws Exception {
        // Given
        String eventCreateRequest = """
                {
                    "name": "Event 1",
                    "date": "2025-09-21",
                    "time": "10:00",
                    "publishAt": "2021-01-01T09:00"
                }
                """;

        // Then
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post("/event")
                .content(eventCreateRequest)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify
        List<EventEntity> eventEntities = eventRepository.findAll();

        EventEntity lastEventEntity = eventEntities.getLast();
        Assertions.assertEquals("Event 1", lastEventEntity.getName());
        Assertions.assertEquals("2025-09-21", lastEventEntity.getDate().toString());
        Assertions.assertEquals("10:00", lastEventEntity.getTime().toString());
        Assertions.assertEquals("2021-01-01T09:00", lastEventEntity.getPublishAt().toString());

        Assertions.assertTrue(lastEventEntity.isPublished());
        Assertions.assertNotNull(lastEventEntity.getCreatedAt());
    }

    @Test
    void givenEventCreateRequestWithoutPublishAt_whenEventCreatedWithIsPublishedTrue_thenReturnOk() throws Exception {
        // Given
        String eventCreateRequest = """
                {
                    "name": "Event 1",
                    "date": "2025-09-21",
                    "time": "10:00"
                }
                """;

        // Then
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post("/event")
                .content(eventCreateRequest)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify
        List<EventEntity> eventEntities = eventRepository.findAll();

        EventEntity lastEventEntity = eventEntities.getLast();
        Assertions.assertEquals("Event 1", lastEventEntity.getName());
        Assertions.assertEquals("2025-09-21", lastEventEntity.getDate().toString());
        Assertions.assertEquals("10:00", lastEventEntity.getTime().toString());
        Assertions.assertNull(lastEventEntity.getPublishAt());

        Assertions.assertTrue(lastEventEntity.isPublished());
        Assertions.assertNotNull(lastEventEntity.getCreatedAt());
    }

    @Test
    void givenEventCreateRequestWithFuturePublishAt_whenEventCreatedWithIsPublishedFalse_thenReturnOk() throws Exception {
        // Given
        String eventCreateRequest = """
                {
                    "name": "Event 1",
                    "date": "3080-09-21",
                    "time": "10:00",
                    "publishAt": "3079-10-21T00:00"
                }
                """;

        // Then
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post("/event")
                .content(eventCreateRequest)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify
        List<EventEntity> eventEntities = eventRepository.findAll();

        EventEntity lastEventEntity = eventEntities.getLast();
        Assertions.assertEquals("Event 1", lastEventEntity.getName());
        Assertions.assertEquals("3080-09-21", lastEventEntity.getDate().toString());
        Assertions.assertEquals("10:00", lastEventEntity.getTime().toString());
        Assertions.assertEquals("3079-10-21T00:00", lastEventEntity.getPublishAt().toString());

        Assertions.assertFalse(lastEventEntity.isPublished());
        Assertions.assertNotNull(lastEventEntity.getCreatedAt());
    }

}