package co.com.seti.jpa.phone;

import co.com.seti.jpa.phone.entities.PhoneEntity;
import co.com.seti.usecase.user.phone.Phone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PhoneRepositoryAdapterTest {


    @Mock
    private PhoneDataRepository repository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PhoneRepositoryAdapter adapter;

    private Phone phone;

    private PhoneEntity phoneEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(objectMapper.map("value", Object.class)).thenReturn("value");

        adapter = new PhoneRepositoryAdapter(repository, objectMapper);

        phone = Phone.builder()
                .userId("1")
                .phoneId("1")
                .number("9")
                .citycode("8")
                .citycode("7")
                .build();

       phoneEntity = PhoneEntity.builder()
               .userId("1")
               .phoneId("1")
               .number("9")
               .citycode("8")
               .citycode("7")
               .build();
    }

    @Test
    void testSave() {

        when(repository.save(Mockito.any())).thenReturn(phoneEntity);
        when(objectMapper.map(phoneEntity, Phone.class)).thenReturn(phone);

        Phone result = adapter.save(phone);

        assertEquals(phone, result);
    }

    @Test
    void testSaveAll() {

        when(repository.save(Mockito.any())).thenReturn(phoneEntity);
        when(objectMapper.map(phoneEntity, Phone.class)).thenReturn(phone);

        List<Phone> result = adapter.saveAll(List.of(phone));

        assertEquals(List.of(phone), result);
    }

    @Test
    void testFindById() {
        when(repository.findById("id")).thenReturn(Optional.of(phoneEntity));
        when(objectMapper.map(phoneEntity, Phone.class)).thenReturn(phone);

        Phone result = adapter.findById("id");

        assertEquals(phone, result);
    }
}
