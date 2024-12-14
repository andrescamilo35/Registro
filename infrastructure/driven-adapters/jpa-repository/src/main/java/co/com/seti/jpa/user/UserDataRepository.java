package co.com.seti.jpa.user;

import co.com.seti.jpa.user.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UserDataRepository extends CrudRepository<UserEntity, String>, QueryByExampleExecutor<UserEntity> {
    Boolean existsByEmail(String email);
}
