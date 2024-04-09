//package s3.ind.business.mapper;
//
//import org.mapstruct.Mapper;
//import s3.ind.domain.request.AdminRequest;
//import s3.ind.domain.request.ClientRequest;
//import s3.ind.domain.request.TrainerRequest;
//import s3.ind.domain.request.UserRequest;
//import s3.ind.domain.response.AdminResponse;
//import s3.ind.domain.response.ClientResponse;
//import s3.ind.domain.response.TrainerResponse;
//import s3.ind.domain.response.UserResponse;
//import s3.ind.persistence.entity.AdminEntity;
//import s3.ind.persistence.entity.ClientEntity;
//import s3.ind.persistence.entity.TrainerEntity;
//import s3.ind.persistence.entity.UserEntity;
//
//import javax.management.relation.InvalidRoleValueException;
//
//@Mapper(componentModel = "spring")
//public interface UserMapper {
//    AdminRequest toAdminRequest(AdminEntity entity);
//    AdminRequest toAdminRequest(UserRequest request);
//    AdminResponse toAdminResponse(AdminEntity entity);
//    AdminEntity toAdminEntity(AdminRequest request);
//
//
//    TrainerRequest toTrainerRequest(TrainerEntity entity);
//    TrainerRequest toTrainerRequest(UserRequest request);
//    TrainerResponse toTrainerResponse(TrainerEntity entity);
//    TrainerEntity toTrainerEntity(TrainerRequest request);
//
//    ClientRequest toClientRequest(ClientEntity entity);
//    ClientRequest toClientRequest(UserRequest request);
//    ClientResponse toClientResponse(ClientEntity entity);
//    ClientEntity toClientEntity(ClientRequest request);
//
//    UserResponse toUserResponse(UserEntity entity);
//
//    default UserEntity toEntity(UserRequest request){
//        if (request instanceof AdminRequest) {
//            return toAdminEntity((AdminRequest) request);
//        } else if (request instanceof TrainerRequest){
//            return toTrainerEntity((TrainerRequest) request);
//        } else if (request instanceof ClientRequest){
//            return toClientEntity((ClientRequest) request);
//        }
//        else {
//            // throw new InvalidRoleValueException("Unknown role!");
//            return null;
//        }
//    }
//
//}
