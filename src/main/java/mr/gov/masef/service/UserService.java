package mr.gov.masef.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mr.gov.masef.entites.User;
import mr.gov.masef.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        // Encoder le mot de passe avant d'enregistrer
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User saveUser(User user) {
    	return userRepository.save(user);
    	
    }
    public Optional<User> findById(long id) {
    	
    	return userRepository.findById(id);
    	
    }
public Optional<User> findByNni(String nni) {
	return userRepository.findByNni(nni);
}
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> 
                new RuntimeException("Utilisateur non trouvé avec le nom d'utilisateur: " + username));
    }
}
