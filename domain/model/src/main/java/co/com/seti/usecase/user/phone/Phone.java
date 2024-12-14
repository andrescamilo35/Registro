package co.com.seti.usecase.user.phone;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    private String phoneId;
    private String number;
    private String citycode;
    private String contrycode;
    private String userId;
}
