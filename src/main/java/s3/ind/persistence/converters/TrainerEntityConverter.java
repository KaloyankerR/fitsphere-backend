package s3.ind.persistence.converters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import s3.ind.domain.Trainer;
import s3.ind.persistence.entity.TrainerEntity;

@Service
@AllArgsConstructor
public class TrainerEntityConverter {
    public Trainer fromEntity(TrainerEntity entity){
        if (entity == null){
            return null;
        }

        return Trainer.builder()
                .id(entity.getUserId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .phoneNumber(entity.getPhoneNumber())
                .bio(entity.getBio())
                .igLink(entity.getIgLink())
                .image(entity.getProfileImageUrl())
                .build();
    }

    public TrainerEntity toEntity(Trainer trainer){
        if (trainer == null){
            return null;
        }

        return TrainerEntity.builder()
                .userId(trainer.getId())
                .firstName(trainer.getFirstName())
                .lastName(trainer.getLastName())
                .email(trainer.getEmail())
                .password(trainer.getPassword())
                .phoneNumber(trainer.getPhoneNumber())
                .bio(trainer.getBio())
                .igLink(trainer.getIgLink())
                .profileImageUrl(trainer.getImage())
                .build();
    }

}
