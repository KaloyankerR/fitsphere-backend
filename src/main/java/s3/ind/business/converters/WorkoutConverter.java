//package s3.ind.business.converters;
//
//import lombok.AllArgsConstructor;
//import s3.ind.business.dto.WorkoutDTO;
//import s3.ind.domain.Workout;
//
//@AllArgsConstructor
//public class WorkoutConverter {
//    private final TrainerConverter trainerConverter;
//
//    public Workout fromDTO(WorkoutDTO dto){
//        if (dto == null){
//            return null;
//        }
//
//        return Workout.builder()
//                .id(dto.getId())
//                .trainer(trainerConverter.fromDTO(dto.getTrainer()))
//                .title(dto.getTitle())
//                .description(dto.getDescription())
//                .build();
//    }
//
//    public WorkoutDTO toDTO(Workout workout) {
//        if (workout == null){
//            return null;
//        }
//
//        return WorkoutDTO.builder()
//                .id(workout.getId())
//                .trainer(trainerConverter.toDTO(workout.getTrainer()))
//                .title(workout.getTitle())
//                .description(workout.getDescription())
//                .build();
//    }
//}
