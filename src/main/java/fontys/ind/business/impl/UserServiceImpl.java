package fontys.ind.business.impl;

import fontys.ind.business.mappers.AdminMapper;
import fontys.ind.domain.response.ApiWrapperResponse;
import fontys.ind.persistence.AdminRepository;
import fontys.ind.persistence.entity.AdminEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import fontys.ind.business.UserService;
import fontys.ind.business.exception.EmailAlreadyExistsException;
import fontys.ind.business.exception.InvalidUserException;
import fontys.ind.business.mappers.ClientMapper;
import fontys.ind.business.mappers.TrainerMapper;
import fontys.ind.business.mappers.UserMapper;
import fontys.ind.domain.request.user.CreateTrainerRequest;
import fontys.ind.domain.request.user.CreateUserRequest;
import fontys.ind.domain.request.user.UpdateUserRequest;
import fontys.ind.domain.response.user.*;
import fontys.ind.persistence.ClientRepository;
import fontys.ind.persistence.TrainerRepository;
import fontys.ind.persistence.UserRepository;
import fontys.ind.domain.RoleEnum;
import fontys.ind.persistence.entity.ClientEntity;
import fontys.ind.persistence.entity.TrainerEntity;
import fontys.ind.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TrainerRepository trainerRepository;
    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;
    private final UserMapper userMapper;
    private final TrainerMapper trainerMapper;
    private final ClientMapper clientMapper;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        UserEntity newUser = createUserEntity(request);

        return CreateUserResponse.builder()
                .userId(Long.valueOf(newUser.getUserId()))
                .build();
    }

    public CreateUserResponse createTrainer(CreateTrainerRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        TrainerEntity newTrainer = (TrainerEntity) createUserEntity(request);

        return CreateUserResponse.builder()
                .userId(Long.valueOf(newTrainer.getUserId()))
                .build();
    }

    private UserEntity createUserEntity(CreateUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        switch (RoleEnum.valueOf(request.getRole())) {
            case CLIENT:
                ClientEntity clientEntity = clientMapper.fromRequestToEntity(request);
                clientEntity.setPassword(encodedPassword);
                return clientRepository.save(clientEntity);

            case TRAINER:
                if (request instanceof CreateTrainerRequest trainerRequest) {
                    TrainerEntity trainerEntity = trainerMapper.fromRequestToEntity(trainerRequest);
                    trainerEntity.setPassword(encodedPassword);
                    return trainerRepository.save(trainerEntity);
                } else {
                    throw new IllegalArgumentException("Invalid request type for trainer creation");
                }

            case ADMIN:
                AdminEntity adminEntity = adminMapper.fromRequestToEntity(request);
                adminEntity.setPassword(encodedPassword);
                return adminRepository.save(adminEntity);

            default:
                UserEntity userEntity = UserEntity.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .password(encodedPassword)
                        .role(RoleEnum.valueOf(request.getRole()))
                        .build();
                return userRepository.save(userEntity);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public Optional<? extends GetUserResponse> getUserById(Integer id) {
        UserEntity userEntity = userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found."));

        return switch (userEntity.getRole()) {
            case TRAINER -> trainerRepository.findById(Long.valueOf(id))
                    .map(trainerMapper::fromEntityToResponse)
                    .or(() -> {
                        throw new EntityNotFoundException("Trainer with ID " + id + " not found.");
                    });
            case ADMIN -> adminRepository.findById(Long.valueOf(id))
                    .map(userMapper::fromEntityToResponse)
                    .or(() -> {
                        throw new EntityNotFoundException("Admin with ID " + id + " not found.");
                    });
            case CLIENT -> clientRepository.findById(Long.valueOf(id))
                    .map(clientMapper::fromEntityToResponse)
                    .or(() -> {
                        throw new EntityNotFoundException("Client with ID " + id + " not found.");
                    });
        };
    }

    @Override
    public ApiWrapperResponse getUsersByRole(String role) {
        return switch (role.toUpperCase()) {
            case "ALL" -> getAllUsers();
            case "ADMIN" -> getAllAdmins();
            case "CLIENT" -> getAllClients();
            case "TRAINER" -> getAllTrainers();
            default -> throw new InvalidUserException("INVALID ROLE!");
        };
    }

    private GetAllUsersResponse getAllAdmins() {
        List<AdminEntity> adminsEntity = adminRepository.findAll();

        List<GetUserResponse> admins = adminsEntity.stream()
                .map(userMapper::fromEntityToResponse)
                .toList();

        GetAllUsersResponse response = new GetAllUsersResponse();
        response.setUsers(admins);
        return response;
    }

    private GetAllClientsResponse getAllClients() {
        List<ClientEntity> clientsEntity = clientRepository.findAll();

        List<GetClientResponse> clients = clientsEntity.stream()
                .map(clientMapper::fromEntityToResponse)
                .toList();

        GetAllClientsResponse response = new GetAllClientsResponse();
        response.setClients(clients);
        return response;
    }

    public GetAllTrainersResponse getAllTrainers() {
        List<TrainerEntity> trainersEntity = trainerRepository.findAll();
        List<GetTrainerResponse> trainers = trainersEntity.stream()
                .map(trainerMapper::fromEntityToResponse)
                .toList();

        GetAllTrainersResponse response = new GetAllTrainersResponse();
        response.setTrainers(trainers);
        return response;
    }

    @Override
    public GetAllUsersResponse getAllUsers() {
        List<UserEntity> usersEntity = userRepository.findAll();

        List<GetUserResponse> users = usersEntity.stream()
                .map(userMapper::fromEntityToResponse)
                .toList();

        final GetAllUsersResponse response = new GetAllUsersResponse();
        response.setUsers(users);
        return response;
    }

    @Override
    public void updateUser(UpdateUserRequest request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new InvalidUserException("USER_ID_INVALID"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        userRepository.save(user);
    }
}
