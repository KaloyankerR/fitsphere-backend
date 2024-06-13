//package fontys.ind.persistence.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.Instant;
//
//@Entity
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "refresh_tokens")
//public class RefreshTokenEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @OneToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private UserEntity user;
//
//    @Column(nullable = false, unique = true)
//    private String token;
//
//    @Column(nullable = false)
//    private Instant expiryDate;
//
//    @Column(nullable = false)
//    private Instant createdAt = Instant.now();
//}
