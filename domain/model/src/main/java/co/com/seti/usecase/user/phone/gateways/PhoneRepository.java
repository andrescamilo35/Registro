package co.com.seti.usecase.user.phone.gateways;

import co.com.seti.usecase.user.phone.Phone;

import java.util.List;

public interface PhoneRepository {
    Phone save(Phone phone);

    List<Phone> saveAll(List<Phone> phones);

    Phone findById(String id);

    Phone update(Phone phone);

    boolean existById(String id);
}
