package s3.ind.configuration.security.token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
