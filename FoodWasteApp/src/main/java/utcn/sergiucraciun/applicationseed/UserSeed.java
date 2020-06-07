package utcn.sergiucraciun.applicationseed;

import utcn.sergiucraciun.models.User;
import utcn.sergiucraciun.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserSeed implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findAll().isEmpty()) {
            userRepository.save(new User("alabala", passwordEncoder.encode("1234")));
            userRepository.save(new User("sergiu", passwordEncoder.encode("abcd")));
            userRepository.save(new User("mihai", passwordEncoder.encode("xyza")));
            userRepository.save(new User("andrei", passwordEncoder.encode("papabun")));
            userRepository.save(new User("maria", passwordEncoder.encode("jocuri")));
        }
    }
}
