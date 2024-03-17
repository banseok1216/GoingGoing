package com.example.goinggoing.user.service;
import com.example.goinggoing.user.mapstruct.UserMapper;
import com.example.goinggoingdomain.domain.user.User;
import com.example.goinggoingdomain.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User loginKakaoUser(User userLogin) {
        User user = userRepository.findByUserEmail(userLogin.getUserEmail());
        if (user == null) {
            user = UserMapper.INSTANCE.toKakaoUser(userLogin);
            userRepository.save(user);
        }
        return user;
    }

    public User getUser(Long id) {
        return userRepository.findByUserId(id);
    }

    public User loginDefaultUser(User userLogin) {
        User user = userRepository.findByUserEmail(userLogin.getUserEmail());
        if (user != null && passwordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }
    public User registerUser(User userRegister) {
        User existingUser = userRepository.findByUserEmail(userRegister.getUserEmail());
        String hashedPassword = passwordEncoder.encode(userRegister.getPassword());
        if (existingUser == null) {
            User newUser = UserMapper.INSTANCE.toDefaultUser(userRegister,hashedPassword);
            userRepository.save(newUser);
            return newUser;
        } else {
            return null;
        }
    }

}
