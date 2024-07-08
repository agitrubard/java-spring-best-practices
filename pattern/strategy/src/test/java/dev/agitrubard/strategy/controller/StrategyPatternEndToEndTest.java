package dev.agitrubard.strategy.controller;

import dev.agitrubard.strategy.model.enums.NotificationType;
import dev.agitrubard.strategy.model.request.NotificationRequest;
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

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class StrategyPatternEndToEndTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenValidNotificationRequest_whenEmailNotificationSent_thenReturnMessage() throws Exception {
        // Given
        NotificationType notificationType = NotificationType.EMAIL;
        NotificationRequest notificationRequest = new NotificationRequest(
                "agitrubard@software.eng",
                notificationType
        );

        // Then
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post("/api/v1/notifications/send")
                .content(notificationRequest.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .value("Email notification sent to agitrubard@software.eng"));
    }

    @Test
    void givenValidNotificationRequest_whenPushNotificationSent_thenReturnMessage() throws Exception {
        // Given
        NotificationType notificationType = NotificationType.PUSH;
        NotificationRequest notificationRequest = new NotificationRequest(
                "agitrubard@software.eng",
                notificationType
        );

        // Then
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post("/api/v1/notifications/send")
                .content(notificationRequest.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .value("Push notification sent to agitrubard@software.eng"));
    }

    @Test
    void givenValidNotificationRequest_whenSMSNotificationSent_thenReturnMessage() throws Exception {
        // Given
        NotificationType notificationType = NotificationType.SMS;
        NotificationRequest notificationRequest = new NotificationRequest(
                "agitrubard@software.eng",
                notificationType
        );

        // Then
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post("/api/v1/notifications/send")
                .content(notificationRequest.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .value("Sms notification sent to agitrubard@software.eng"));
    }

}