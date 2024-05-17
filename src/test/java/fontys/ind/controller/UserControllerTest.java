package fontys.ind.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import fontys.ind.business.UserService;
import fontys.ind.business.exception.InvalidUserException;
import fontys.ind.domain.response.user.GetAllUsersResponse;
import fontys.ind.domain.response.user.GetUserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)

class UserControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void getAllUsers() throws Exception {
        // Create a list of GetUserResponse
        List<GetUserResponse> userList = new ArrayList<>();
        GetUserResponse user = new GetUserResponse(); // Assuming default constructor and setters
        user.setId(1);
        user.setFirstName("Kalo");
        userList.add(user);

        // Create GetAllUsersResponse with the list
        GetAllUsersResponse allUsersResponse = GetAllUsersResponse.builder()
                .users(userList)
                .build();

        // Mocking the service call
        when(userService.getAllUsers()).thenReturn(allUsersResponse);

        // Perform the mock MVC call
        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that the service method was called
        verify(userService).getAllUsers();
    }

//    @Test
//    public void testGetUsersByRole_Success() throws Exception {
//        // Create a mock ApiWrapperResponse
//        ApiWrapperResponse expectedResponse = new ApiWrapperResponse();
//
//        // Properly set up the stubbing
//        when(userService.getUsersByRole("TRAINER")).thenReturn(expectedResponse);
//
//        // Perform GET request
//        mockMvc.perform(get("/users/role/{role}", "TRAINER")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//
//        // Verify interactions
//        verify(userService).getUsersByRole("TRAINER");
//    }

    @Test
    public void testGetUsersByRole_InvalidRole() throws Exception {
        // Setup exception handling
        when(userService.getUsersByRole("INVALID_ROLE")).thenThrow(new InvalidUserException("Invalid role"));

        // Perform GET request
        mockMvc.perform(get("/users/role/{role}", "INVALID_ROLE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("400 BAD_REQUEST \"Invalid role\""));

        // Verify interactions
        verify(userService).getUsersByRole("INVALID_ROLE");
    }

}