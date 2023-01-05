package com.codelib.securitydataintegration;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        User principal = new User();
        principal.setId(customUser.id());
        principal.setEmail(customUser.email());
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, "password",
                AuthorityUtils.createAuthorityList("ROLE_USER"));
        context.setAuthentication(auth);

        return context;
    }
}
