//package s3.ind.persistence.converters;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import s3.ind.domain.User;
//import s3.ind.persistence.entity.UserEntity;
//
//@Service
//@AllArgsConstructor
//public class UserEntityConverter {
//    public User fromEntity(UserEntity entity) {
//        if (entity == null) {
//            return null;
//        }
//
//        return User.builder()
//                .id(entity.getUserId())
//                .firstName(entity.getFirstName())
//                .lastName(entity.getLastName())
//                .email(entity.getEmail())
//                .password(entity.getPassword())
//                .role(entity.getRole())
//                .build();
//    }
//
//    public UserEntity toEntity(User user) {
//        if (user == null) {
//            return null;
//        }
//
//        return UserEntity.builder()
//                .userId(user.getId())
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .email(user.getEmail())
//                .password(user.getPassword())
//                .role(user.getRole())
//                .build();
//    }
//}
