package ma.example.scenariosensor.service;

import lombok.RequiredArgsConstructor;
import ma.example.scenariosensor.entity.User;
import ma.example.scenariosensor.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
