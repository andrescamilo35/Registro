package co.com.seti.api.user.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Builder
public record RegisterRequest (
    @NotBlank(message = "El nombre no puede estar vacio")
    String name,
    @Email
    @NotBlank(message = "El email no puede estar vacio")
    String email,

    @NotBlank(message = "la contraseña no puede estar vacia")
    @Length(max = 20, min = 8,message = "La contraseña de tener una longitud en un rango entre 8 y 20")
    @Pattern( regexp = "^(?!.*\\s)(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–\\[{}\\]:;',?/*~$^+=<>]).{8,20}$",
            message = "Recuerde que la contraseña debe tener al menos un carácter especial, " +
                    "una letra mayúscula, una minúscula y un numero.   "
    )
    String password,

    @NotEmpty(message = "El telefono no puede estar vacio")
    List<PhoneResquet> phones
){}
