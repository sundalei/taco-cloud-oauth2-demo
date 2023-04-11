package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import tacos.domain.TacoUser;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String role;

    public TacoUser toUser(PasswordEncoder passwordEncoder) {
        return new TacoUser(username, passwordEncoder.encode(password),
                fullname, street, city, state, zip, phone, role);
    }
}
