package com.technokratos.minimyini.security.filter;

import com.technokratos.minimyini.security.TokenProvider;
import com.technokratos.minimyini.security.UserDetailsImpl;
import com.technokratos.minimyini.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = tokenProvider.getTokenFromRequest(request);
        if (tokenProvider.validate(token)) {
            Long userId = tokenProvider.getUserIdFromToken(token);
            UserDetailsImpl userDetails = new UserDetailsImpl(userService.findById(userId));
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else response.setStatus(HttpStatus.UNAUTHORIZED.value());

        filterChain.doFilter(request, response);
    }
}
