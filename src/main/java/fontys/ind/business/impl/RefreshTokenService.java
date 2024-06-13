//package fontys.ind.business.impl;
//
//import fontys.ind.persistence.RefreshTokenRepository;
//import fontys.ind.persistence.UserRepository;
//import fontys.ind.persistence.entity.RefreshTokenEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class RefreshTokenService {
//    @Autowired
//    RefreshTokenRepository refreshTokenRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    public RefreshTokenEntity createRefreshToken(String email) {
//        RefreshTokenEntity refreshToken = RefreshTokenEntity.builder()
//                .user(userRepository.findByEmail(email))
//                .token(UUID.randomUUID().toString())
//                .expiryDate(Instant.now().plusMillis(600000)) // set expiry of refresh token to 10 minutes - you can configure it application.properties file
//                .build();
//        return refreshTokenRepository.save(refreshToken);
//    }
//
//
//    public Optional<RefreshTokenEntity> findByToken(String token) {
//        return refreshTokenRepository.findByToken(token);
//    }
//
//    public RefreshTokenEntity verifyExpiration(RefreshTokenEntity token) {
//        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
//            refreshTokenRepository.delete(token);
//            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
//        }
//        return token;
//    }
//}
