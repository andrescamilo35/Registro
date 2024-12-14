package co.com.seti.jpa.user;

import co.com.seti.jpa.user.entities.UserEntity;
import co.com.seti.usecase.user.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserRepositoryAdapterTest {


    @Mock
    private UserDataRepository repository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private UserRepositoryAdapter adapter;

    private User user;

    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(objectMapper.map("value", Object.class)).thenReturn("value");

        adapter = new UserRepositoryAdapter(repository, objectMapper);

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

       userEntity = UserEntity.builder()
               .email("xxx@xxx.com")
               .name("")
               .userId("")
               .phones(new ArrayList<>())
               .lastLogin(LocalDateTime.now())
               .created(LocalDateTime.now())
               .modified(LocalDateTime.now())
               .isactive(true)
               .build();
    }

    @Test
    void testSave() {

        when(repository.save(Mockito.any())).thenReturn(userEntity);
        when(objectMapper.map(userEntity, User.class)).thenReturn(user);

        User result = adapter.save(user);

        assertEquals(user, result);
    }

    @Test
    void testFindById() {
        when(repository.findById("id")).thenReturn(Optional.of(userEntity));
        when(objectMapper.map(userEntity, User.class)).thenReturn(user);

        User result = adapter.findById("id");

        assertEquals(user, result);
    }
}
