package co.com.seti.usecase.user.jwt.gateway;

import java.util.Map;

public interface JWTGateway {
    String generateToken(String user, Map<String, Object> claims);
    String encritarPassword(String password);
}
