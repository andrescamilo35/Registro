package co.com.seti.jpa.phone.entities;

import co.com.seti.jpa.user.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "phone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String phoneId;
    private String number;
    private String citycode;
    private String contrycode;
    private String userId;
}
