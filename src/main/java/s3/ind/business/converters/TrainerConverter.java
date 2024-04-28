package s3.ind.business.converters;

import s3.ind.business.dto.TrainerDTO;
import s3.ind.domain.Trainer;

public class TrainerConverter {
    public Trainer fromDTO(TrainerDTO dto) {
        if (dto == null) {
            return null;
        }

        return Trainer.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
//                .phoneNumber(dto.getPhoneNumber())
                .bio(dto.getBio())
                .igLink(dto.getIgLink())
                .image(dto.getImage())
                .build();
    }

    public TrainerDTO toDTO(Trainer trainer) {
        if (trainer == null) {
            return null;
        }

        return TrainerDTO.builder()
                .id(trainer.getId())
                .firstName(trainer.getFirstName())
                .lastName(trainer.getLastName())
                .email(trainer.getEmail())
                .password(trainer.getPassword())
//                .phoneNumber(trainer.getPhoneNumber())
                .bio(trainer.getBio())
                .igLink(trainer.getIgLink())
                .image(trainer.getImage())
                .build();

    }
}
