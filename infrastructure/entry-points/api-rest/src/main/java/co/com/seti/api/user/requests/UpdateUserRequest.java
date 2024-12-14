package co.com.seti.api.user.requests;

import co.com.seti.usecase.user.phone.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class UpdateUserRequest {
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String name;
    @Email
    @NotEmpty(message = "El email no puede estar vacio")
    private String email;
    @NotEmpty(message = "El telefono no puede estar vacio")
    private List<Phone> phones;
}
