package co.com.seti.usecase.user;

import co.com.seti.usecase.user.common.exception.ErrorException;
import co.com.seti.usecase.user.jwt.gateway.JWTGateway;
import co.com.seti.usecase.user.phone.Phone;
import co.com.seti.usecase.user.phone.gateways.PhoneRepository;
import co.com.seti.usecase.user.user.User;
import co.com.seti.usecase.user.user.gateways.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;

class RegisterUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PhoneRepository phoneRepository;

    @Mock
    private JWTGateway jwtGateway;

    @InjectMocks
    private RegisterUseCase registerUseCase;

    private User user;

    private Phone phone;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = User.builder()
                .email("xxx@xxx.com")
                .name("")
                .userId("")
                .phones(new ArrayList<>())
                .lastLogin(LocalDateTime.now())
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .isactive(true)
                .build();

        phone = Phone.builder()
                .userId("1")
                .phoneId("1")
                .number("9")
                .citycode("8")
                .citycode("7")
                .build();

    }

    @Test
    void createUserExitoso() throws ErrorException {

        when(phoneRepository.saveAll(Mockito.any())).thenReturn(List.of(phone));
        when(userRepository.save(Mockito.any())).thenReturn(user);

        User result = registerUseCase.save(user);

        assertThat(result).isNotNull();
        Assertions.assertEquals(user.getUserId(), Objects.requireNonNull(result.getUserId()));
    }

    @Test
    void getUserError() {
        when(userRepository.findById(Mockito.anyString())).thenThrow(new NullPointerException());

        Assertions.assertThrows(NullPointerException.class, () ->
                registerUseCase.findById("1"));
    }

    @Test
    void createUserError(){

        when(userRepository.existByEmail(Mockito.any())).thenReturn(true);

        Assertions.assertThrows(ErrorException.class, () ->
                registerUseCase.save(user));
    }

}