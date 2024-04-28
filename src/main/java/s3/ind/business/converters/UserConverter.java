package s3.ind.business.converters;

import s3.ind.business.dto.UserDTO;
import s3.ind.domain.User;

public class UserConverter {
    public User fromDTO(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        return User.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                // .password()
//                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                // .password()
//                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
