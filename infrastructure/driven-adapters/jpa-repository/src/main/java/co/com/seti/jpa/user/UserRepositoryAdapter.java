package co.com.seti.jpa.user;

import co.com.seti.jpa.helper.AdapterOperations;
import co.com.seti.jpa.user.entities.UserEntity;
import co.com.seti.usecase.user.user.User;
import co.com.seti.usecase.user.user.gateways.UserRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryAdapter extends AdapterOperations<User, UserEntity, String, UserDataRepository>
 implements UserRepository
{

    public UserRepositoryAdapter(UserDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, User.class));
    }



    @Override
    public User update(User user) {
        return this.save(user);
    }

    @Override
    public boolean existById(String id) {
        return this.repository.existsById(id);
    }

    @Override
    public boolean existByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
