package fontys.ind.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import fontys.ind.business.UserService;
import fontys.ind.domain.response.user.GetAllTrainersResponse;
import fontys.ind.domain.response.user.GetTrainerResponse;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void getAllTrainersTest() throws Exception {
        GetAllTrainersResponse mockResponse = new GetAllTrainersResponse(
                Arrays.asList(
                        GetTrainerResponse.builder()
                                .id(1).firstName("John").build(),
                        GetTrainerResponse.builder()
                                .id(2).firstName("Annie").build()));

        when(userService.getAllTrainers()).thenReturn(mockResponse);

        mockMvc.perform(get("/users/trainers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService).getAllTrainers();
    }

}