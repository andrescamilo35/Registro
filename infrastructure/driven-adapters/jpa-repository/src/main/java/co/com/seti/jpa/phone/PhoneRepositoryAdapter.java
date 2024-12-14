package co.com.seti.jpa.phone;

import co.com.seti.jpa.helper.AdapterOperations;
import co.com.seti.jpa.phone.entities.PhoneEntity;
import co.com.seti.usecase.user.phone.Phone;
import co.com.seti.usecase.user.phone.gateways.PhoneRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhoneRepositoryAdapter extends AdapterOperations<Phone, PhoneEntity, String, PhoneDataRepository>
 implements PhoneRepository
{

    public PhoneRepositoryAdapter(PhoneDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Phone.class));
    }

    @Override
    public List<Phone> saveAll(List<Phone> phones) {
        return this.saveAllEntities(phones);
    }

    @Override
    public Phone update(Phone user) {
        return this.save(user);
    }

    @Override
    public boolean existById(String id) {
        return this.repository.existsById(id);
    }

}
