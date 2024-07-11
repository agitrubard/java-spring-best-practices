package dev.agitrubard.factory.controller;

import dev.agitrubard.factory.model.enums.TwoFactorAuthenticationType;
import dev.agitrubard.factory.model.request.LoginRequest;
import dev.agitrubard.factory.model.request.RegisterRequest;
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
class FactoryPatternEndToEndTest {

    @Autowired
    private MockMvc mockMvc;


    private static final String REGISTER_ENDPOINT = "/api/v1/auth/register";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";


    private static final String PASSKEY_AUTHENTICATION_RESPONSE_MESSAGE = "User authenticating via Passkey...";
    private static final String EMAIL_AUTHENTICATION_RESPONSE_MESSAGE = "User authenticating via Email...";
    private static final String SMS_AUTHENTICATION_RESPONSE_MESSAGE = "User authenticating via SMS...";


    /**
     * End to end tests for the register and login endpoint
     */
    @Test
    void givenValidRegisterAndLoginRequests_whenUserAuthenticatesWithPasskey_thenReturnsRelatedMessage() throws Exception {

        // Given - Register
        RegisterRequest registerRequest = new RegisterRequest(
                "agitrubard",
                "1234",
                "agitrubard@software.eng",
                "1234567890",
                TwoFactorAuthenticationType.PASSKEY
        );

        // Then - Register
        MockHttpServletRequestBuilder mockHttpServletRegisterRequestBuilder = MockMvcRequestBuilders
                .post(REGISTER_ENDPOINT)
                .content(registerRequest.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockHttpServletRegisterRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .value(PASSKEY_AUTHENTICATION_RESPONSE_MESSAGE));


        // Given - Login
        LoginRequest loginRequest = new LoginRequest(
                "agitrubard",
                "1234"
        );

        // Then - Login
        MockHttpServletRequestBuilder mockHttpServletLoginRequestBuilder = MockMvcRequestBuilders
                .post(LOGIN_ENDPOINT)
                .content(loginRequest.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockHttpServletLoginRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .value(PASSKEY_AUTHENTICATION_RESPONSE_MESSAGE));
    }


    /**
     * End to end tests for the register endpoint
     */
    @Test
    void givenRegisterRequestWithoutTwoFactorAuthentication_whenUserAuthenticatesViaDefaultAuthenticationType_thenReturnRelatedMessage() throws Exception {
        // Given
        RegisterRequest registerRequest = new RegisterRequest(
                "agitrubard1",
                "1234",
                "agitrubard1@software.eng",
                "1234567891",
                null
        );

        // Then
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post(REGISTER_ENDPOINT)
                .content(registerRequest.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .value(EMAIL_AUTHENTICATION_RESPONSE_MESSAGE));
    }

    @Test
    void givenRegisterRequestWithTwoFactorAuthentication_whenUserAuthenticatesViaPasskey_thenReturnRelatedMessage() throws Exception {
        // Given
        TwoFactorAuthenticationType authenticationType = TwoFactorAuthenticationType.PASSKEY;
        RegisterRequest registerRequest = new RegisterRequest(
                "agitrubard2",
                "1234",
                "agitrubard2@software.eng",
                "1234567892",
                authenticationType
        );

        // Then
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post(REGISTER_ENDPOINT)
                .content(registerRequest.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .value(PASSKEY_AUTHENTICATION_RESPONSE_MESSAGE));
    }

    @Test
    void givenRegisterRequestWithTwoFactorAuthentication_whenUserAuthenticatesViaEmail_thenReturnRelatedMessage() throws Exception {
        // Given
        TwoFactorAuthenticationType authenticationType = TwoFactorAuthenticationType.EMAIL;
        RegisterRequest registerRequest = new RegisterRequest(
                "agitrubard3",
                "1234",
                "agitrubard3@software.eng",
                "1234567893",
                authenticationType
        );

        // Then
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post(REGISTER_ENDPOINT)
                .content(registerRequest.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .value(EMAIL_AUTHENTICATION_RESPONSE_MESSAGE));
    }

    @Test
    void givenRegisterRequestWithTwoFactorAuthentication_whenUserAuthenticatesViaSMS_thenReturnRelatedMessage() throws Exception {
        // Given
        TwoFactorAuthenticationType authenticationType = TwoFactorAuthenticationType.SMS;
        RegisterRequest registerRequest = new RegisterRequest(
                "agitrubard4",
                "1234",
                "agitrubard4@software.eng",
                "1234567894",
                authenticationType
        );

        // Then
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post(REGISTER_ENDPOINT)
                .content(registerRequest.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .value(SMS_AUTHENTICATION_RESPONSE_MESSAGE));
    }


    /**
     * End to end tests for the login endpoint
     */
    @Test
    void givenLoginRequest_whenUserAuthenticatesViaPasskey_thenReturnRelatedMessage() throws Exception {

        // Given
        LoginRequest loginRequest = new LoginRequest(
                "user1",
                "password1"
        );

        // Then
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post(LOGIN_ENDPOINT)
                .content(loginRequest.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .value(PASSKEY_AUTHENTICATION_RESPONSE_MESSAGE));
    }

    @Test
    void givenLoginRequest_whenUserAuthenticatesViaEmail_thenReturnRelatedMessage() throws Exception {

        // Given
        LoginRequest loginRequest = new LoginRequest(
                "user2",
                "password2"
        );

        // Then
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post(LOGIN_ENDPOINT)
                .content(loginRequest.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .value(EMAIL_AUTHENTICATION_RESPONSE_MESSAGE));
    }

    @Test
    void givenLoginRequest_whenUserAuthenticatesViaSMS_thenReturnRelatedMessage() throws Exception {

        // Given
        LoginRequest loginRequest = new LoginRequest(
                "user3",
                "password3"
        );

        // Then
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post(LOGIN_ENDPOINT)
                .content(loginRequest.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .value(SMS_AUTHENTICATION_RESPONSE_MESSAGE));
    }

}