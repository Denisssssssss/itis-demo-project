package com.technokratos.minimyini.security;

import javax.servlet.http.HttpServletRequest;

public interface TokenProvider {

    String generate(Long id);

    boolean validate(String token);

    Long getUserIdFromToken(String token);

    String getTokenFromRequest(HttpServletRequest request);
}
