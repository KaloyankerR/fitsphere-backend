package fontys.ind.configuration.security.token.impl;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import fontys.ind.configuration.security.token.AccessToken;

import java.util.Collections;
import java.util.Set;

@EqualsAndHashCode
@Getter
public class AccessTokenImpl implements AccessToken {
    private final String subject;
    private final Long userId;
    private final Set<String> roles;

    public AccessTokenImpl(String subject, Long userId, String role) {
        this.subject = subject;
        this.userId = userId;
        this.roles = role != null ? Collections.singleton(role) : Collections.emptySet();
    }

    @Override
    public boolean hasRole(String roleName) {
        return this.roles.contains(roleName);
    }
}
