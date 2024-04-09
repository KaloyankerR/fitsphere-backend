//package s3.ind.configuration.security.token;
//
//import fontys.gp.s3.domain.request.LoginRequest;
//import fontys.gp.s3.domain.response.LoginResponse;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.security.InvalidKeyException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//
//@Component
//public class CsrfTokenManager {
//    private static final String SECRET_KEY = "E91E158E4C6656F68B1B5D1C316766DE98D2AD6EF3BFB44F78E9CFCDF5";
//
//    public static String signCsrfToken(String csrfToken) {
//        try {
//            Mac hmac = Mac.getInstance("HmacSHA256");
//            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256");
//            hmac.init(secretKey);
//            byte[] signatureBytes = hmac.doFinal(csrfToken.getBytes());
//            return Base64.getUrlEncoder().withoutPadding().encodeToString(signatureBytes);
//        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static boolean verifySignedToken(String csrfToken, String signedToken) {
//        try {
//            Mac hmac = Mac.getInstance("HmacSHA256");
//            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256");
//            hmac.init(secretKey);
//            byte[] expectedSignature = hmac.doFinal(csrfToken.getBytes());
//            byte[] actualSignature = Base64.getUrlDecoder().decode(signedToken);
//            return MessageDigest.isEqual(expectedSignature, actualSignature);
//        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//}
//
