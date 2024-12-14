package co.com.seti.api.user.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class InfoRegister {

    private String id;
    private String token;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime last_login;
    private boolean isactive;
}
