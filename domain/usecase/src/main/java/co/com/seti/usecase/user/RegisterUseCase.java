package co.com.seti.usecase.user;

import co.com.seti.usecase.user.common.exception.ErrorException;
import co.com.seti.usecase.user.common.exception.codigoError;
import co.com.seti.usecase.user.jwt.gateway.JWTGateway;
import co.com.seti.usecase.user.phone.Phone;
import co.com.seti.usecase.user.phone.gateways.PhoneRepository;
import co.com.seti.usecase.user.user.User;
import co.com.seti.usecase.user.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;


@RequiredArgsConstructor
public class RegisterUseCase {

    private final UserRepository userRepository;

    private final PhoneRepository phoneRepository;

    private final JWTGateway jwtGateway;

    public User save(User user) throws ErrorException {
        boolean existingUser = this.userRepository.existByEmail(user.getEmail());

        if (existingUser) {
            throw new ErrorException("El correo ya registrado", codigoError.FOUND.getCode());
        }

        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setIsactive(true);
        user.setPassword(jwtGateway.encritarPassword(user.getPassword()));
        User userResult = this.userRepository.save(user);

        phoneRepository.saveAll(user.getPhones().stream()
                .map(phoneRequest -> Phone.builder()
                        .contrycode(phoneRequest.getContrycode())
                        .number(phoneRequest.getNumber())
                        .citycode(phoneRequest.getCitycode())
                        .userId(userResult.getUserId())
                        .build())
                .toList());

        HashMap<String,Object> map = new HashMap<>();
        map.put("email", user.getEmail());
        map.put("rol", "CLIENT");
        userResult.setToken(jwtGateway.generateToken(user.getUserId(),map));

        return this.userRepository.save(userResult);
    }

    public User findById(String id) throws ErrorException {
        User user = this.userRepository.findById(id);

        if (user == null)
            throw new ErrorException("El usuario no existe", codigoError.NOT_FOUND.getCode());

        return user;
    }

    public User updateUser(User user) {
        //Logica

        boolean existingUser = this.userRepository.existById(String.valueOf(user.getUserId()));

        if (!existingUser) {
            //throw new Exception()
        }
        return this.userRepository.update(user);
    }
}
