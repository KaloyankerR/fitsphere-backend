package fontys.ind.business.impl;

import fontys.ind.domain.response.ApiWrapperResponse;
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
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    // Repositories
    private final UserRepository userRepository;
    private final TrainerRepository trainerRepository;
    private final ClientRepository clientRepository;
    // Mapper
    private final UserMapper userMapper;
    private final TrainerMapper trainerMapper;
    private final ClientMapper clientMapper;
    // private final AppointmentMapper appointmentMapper;

    private final PasswordEncoder passwordEncoder;

    // CREATE
    @Transactional
    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        UserEntity newUser;

        if (Objects.equals(request.getRole(), "CLIENT")) {
            newUser = saveNewClient(request);
        } else {
            newUser = saveNewUser(request);
        }

        return CreateUserResponse.builder()
                .userId(Long.valueOf(newUser.getUserId()))
                .build();
    }

    private ClientEntity saveNewClient(CreateUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());


        ClientEntity clientEntity = clientMapper.fromRequestToEntity(request);
        clientEntity.setPassword(encodedPassword);

        return clientRepository.save(clientEntity);
    }

    @Transactional
    public CreateUserResponse createTrainer(CreateTrainerRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        TrainerEntity newTrainer = saveTrainer(request);

        return CreateUserResponse.builder()
                .userId(Long.valueOf(newTrainer.getUserId()))
                .build();
    }

    private UserEntity saveNewUser(CreateUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity newUser = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(encodedPassword)
                .role(RoleEnum.valueOf(request.getRole()))
                .build();

        return userRepository.save(newUser);
    }

    private TrainerEntity saveTrainer(CreateTrainerRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        TrainerEntity newTrainer = TrainerEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(encodedPassword)
                .role(RoleEnum.valueOf(request.getRole()))
                .bio(request.getBio())
                .igLink(request.getIgLink())
                .profileImageUrl(request.getProfileImageUrl())
                .build();

        return userRepository.save(newTrainer);
    }

    // DELETE
    @Override
    @Transactional
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    // GET
    @Override
    @Transactional
    public Optional<GetUserResponse> getUserById(Integer id) {
        UserEntity userEntity = userRepository.getUserEntityByUserId(id);

        if (userEntity != null) {
            return switch (userEntity.getRole()) {
                case TRAINER -> {
                    Optional<TrainerEntity> trainerEntity = trainerRepository.findById(Long.valueOf(id));
                    yield trainerEntity.map(trainerMapper::fromEntityToResponse);
                }
                case ADMIN ->
                    // Optional<AdminEntity> adminEntity = admin TODO: finish
                    Optional.ofNullable(userMapper.fromEntityToResponse(userRepository.getUserEntityByUserId(id)));
                case CLIENT -> {
                    Optional<ClientEntity> clientEntity = clientRepository.findById(Long.valueOf(id));
                    yield clientEntity.map(clientMapper::fromEntityToResponse);
                }
            };
        }

        return Optional.ofNullable(userMapper.fromEntityToResponse(userRepository.getUserEntityByUserId(id)));
    }

    // GET ALL
    @Override
    @Transactional
    public ApiWrapperResponse getUsersByRole(String role) {
        return switch (role.toUpperCase()) {
            case "ADMIN" ->
                // TODO: create getAllAdmins()
                    getAllUsers();
            case "CLIENT" ->
                // TODO: create getAllClients()
                    getAllUsers();
            case "TRAINER" -> getAllTrainers();
            default -> throw new InvalidUserException("Invalid role!");
        };
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
    @Transactional
    public GetAllTrainersResponse getAllTrainers() {
        List<TrainerEntity> trainersEntity = trainerRepository.findAll();
        List<GetTrainerResponse> trainers = trainersEntity.stream()
                .map(trainerMapper::fromEntityToResponse)
                .toList();

        final GetAllTrainersResponse response = new GetAllTrainersResponse();
        response.setTrainers(trainers);
        return response;
    }

    // UPDATE
    @Override
    @Transactional
    public void updateUser(UpdateUserRequest request) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(request.getUserId());

        if (userEntityOptional.isEmpty()) {
            throw new InvalidUserException("USER_ID_INVALID");
        }

        UserEntity user = userEntityOptional.get();
        updateFields(request, user);
    }

    private void updateFields(UpdateUserRequest request, UserEntity user) {
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        userRepository.save(user);
    }
}
