package com.technokratos.minimyini.security.filter;

import com.technokratos.minimyini.dto.UserDto;
import com.technokratos.minimyini.model.User;
import com.technokratos.minimyini.security.UserDetailsImpl;
import com.technokratos.minimyini.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TelegramAuthenticationFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String chatId = request.getHeader("chatId");
        if (chatId != null) {
            User user = userService.findByUsername(chatId).orElseThrow(EntityNotFoundException::new);
            if (user.getLastAuth().plusDays(7).isAfter(LocalDate.now())) {
                UserDetailsImpl userDetails = new UserDetailsImpl(user);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}
