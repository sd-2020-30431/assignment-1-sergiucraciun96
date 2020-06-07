package utcn.sergiucraciun.services;

import utcn.sergiucraciun.models.User;
import utcn.sergiucraciun.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> login(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
