package co.com.seti.api.user;

import co.com.seti.api.user.requests.RegisterRequest;
import co.com.seti.api.user.response.InfoRegister;
import co.com.seti.usecase.user.common.exception.ErrorException;
import co.com.seti.usecase.user.phone.Phone;
import co.com.seti.usecase.user.user.User;
import co.com.seti.usecase.user.RegisterUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    private final RegisterUseCase registerUseCase;


    @GetMapping(path = "/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public User getUser(@PathVariable("id") String id) throws ErrorException {
        return registerUseCase.findById(id);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<InfoRegister> registerUser(@Valid @RequestBody RegisterRequest request) throws ErrorException {
        User user = User.builder()
                .name(request.name())
                .password(request.password())
                .email(request.email())
                .phones(request.phones().stream()
                        .map(phoneRequest -> Phone.builder()
                                .contrycode(phoneRequest.getContrycode())
                                .number(phoneRequest.getNumber())
                                .citycode(phoneRequest.getCitycode())
                                .build())
                        .toList())
                .build();


        User savedUser = registerUseCase.save(user);
        return ResponseEntity.ok(InfoRegister.builder()
                        .id(savedUser.getUserId())
                        .created(savedUser.getCreated())
                        .modified(savedUser.getModified())
                        .last_login(savedUser.getLastLogin())
                        .token(savedUser.getToken())
                        .isactive(savedUser.isIsactive())
                .build());
    }
}
