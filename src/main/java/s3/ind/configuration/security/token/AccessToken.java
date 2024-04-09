package s3.ind.configuration.security.token;

import java.util.Set;

public interface AccessToken {
    String getSubject();

    Integer getUserId();
    String getRole();

    Set<String> getRoles();

    boolean hasRole(String roleName);
}
