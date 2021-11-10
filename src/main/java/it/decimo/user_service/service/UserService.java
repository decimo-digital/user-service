package it.decimo.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.decimo.user_service.model.AuthUser;
import it.decimo.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Servizio che si occupa di gestire le chiamate ricevute dal
 * {@link it.decimo.user_service.controller.UserController}
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Recupera le informazioni dell'utente
     */
    public AuthUser getAuthUser(int id) {
        final var user = userRepository.findById(id);
        if (!user.isPresent()) {
            log.info("User of id {} doesn't exists", id);
        }
        return user.orElse(null);
    }

    /**
     * Registra una nuova utenza
     */
    public AuthUser register(AuthUser dto) {
        return userRepository.save(dto);
    }
}
