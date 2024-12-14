package co.com.seti.usecase.user.user.gateways;

import co.com.seti.usecase.user.user.User;

public interface UserRepository {
    User save(User user);

    User findById(String id);

    User update(User user);

    boolean existById(String id);

    boolean existByEmail(String email);
}
