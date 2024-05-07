package fontys.ind.persistence.entity;//package s3.ind.persistence.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//@Entity
//@Table(name = "ClientProgress")
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class ClientProgressEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ProgressID")
//    private Integer progressId;
//
//    @Column(name = "ClientUserID", nullable = false)
//    private Integer clientUserId;
//
//    @Temporal(TemporalType.DATE)
//    @Column(name = "DateRecorded", nullable = false)
//    private Date dateRecorded;
//
//    @Column(name = "Weight", precision = 5, scale = 2)
//    private BigDecimal weight;
//
//    @Column(name = "FatPercentage", precision = 5, scale = 2)
//    private BigDecimal fatPercentage;
//
//    @Column(name = "FatMass", precision = 5, scale = 2)
//    private BigDecimal fatMass;
//
//    @Column(name = "MuscleMass", precision = 5, scale = 2)
//    private BigDecimal muscleMass;
//
//    @Column(name = "BmiIndex", precision = 5, scale = 2)
//    private BigDecimal bmiIndex;
//
//    @Column(name = "Impedence")
//    private Integer impedence;
//
//    @ManyToOne
//    @JoinColumn(name = "ClientUserID", referencedColumnName = "UserID", insertable = false, updatable = false)
//    private ClientEntity client;
//}
