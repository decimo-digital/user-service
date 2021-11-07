package it.decimo.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.decimo.user_service.model.UserInfo;
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
    public UserInfo getUserInfo(int id) {
        final var user = userRepository.findById(id);
        if (!user.isPresent()) {
            log.info("User of id {} doesn't exists", id);
        }
        return user.orElse(null);
    }

    /**
     * Registra una nuova utenza
     */
    public UserInfo register(UserInfo dto) {
        return userRepository.save(dto);
    }
}
