package co.com.seti.api.user.requests;

import lombok.Data;

@Data
public class PhoneResquet {
    private String number;
    private String citycode;
    private String contrycode;
}
