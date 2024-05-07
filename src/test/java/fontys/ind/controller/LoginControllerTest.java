package fontys.ind.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import fontys.ind.business.LoginUseCase;
import fontys.ind.domain.request.LoginRequest;
import fontys.ind.domain.response.LoginResponse;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoginControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LoginUseCase loginUseCase;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    void testSuccessfulLogin() throws Exception {
        LoginRequest loginRequest = new LoginRequest("user@example.com", "password123");
        LoginResponse loginResponse = LoginResponse.builder().accessToken("access_token").build();

        given(loginUseCase.login(loginRequest)).willReturn(loginResponse);

        mockMvc.perform(post("/tokens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@example.com\", \"password\":\"password123\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken").value("access_token"));
    }

//    @Test
//    public void testInvalidCredentials() throws Exception {
//        LoginRequest loginRequest = new LoginRequest("user@example.com", "wrongpassword");
//
//        given(loginUseCase.login(loginRequest)).willThrow(new InvalidCredentialsException());
//
//        mockMvc.perform(post("/tokens")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"email\":\"user@example.com\", \"password\":\"wrongpassword\"}"))
//                .andExpect(status().isUnauthorized());
//    }
    // TODO: Uncomment when you are not testing the APIs (allowing all methods)

    @Test
    void testInvalidRequest() throws Exception {
        mockMvc.perform(post("/tokens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"\", \"password\":\"\"}"))
                .andExpect(status().isBadRequest());
    }
}
