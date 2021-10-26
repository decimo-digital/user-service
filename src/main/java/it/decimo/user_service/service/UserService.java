package it.decimo.user_service.service;

import it.decimo.user_service.dto.RegistrationDto;
import it.decimo.user_service.dto.responses.UserInfoResponse;
import it.decimo.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servizio che si occupa di gestire le chiamate ricevute dal {@link it.decimo.user_service.controller.UserController}
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Recupera le informazioni dell'utente
     */
    public UserInfoResponse getUserInfo(int id) {
        return userRepository.getUserInfoById(id);
    }

    /**
     * Registra una nuova utenza
     */
    public boolean register(RegistrationDto dto) {
        return userRepository.register(dto);
    }
}
