package it.decimo.user_service.repository;

import it.decimo.user_service.dto.RegistrationDto;
import it.decimo.user_service.dto.responses.UserInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Recupera le informazioni di base dell'utente
     *
     * @param id L'utente di cui ci interessano le informazioni
     * @return Le informazioni dell'utente
     */
    public UserInfoResponse getUserInfoById(int id) {
        final var query = "SELECT id, first_name, last_name, email, phone, propic, referral FROM user_identity WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(query, UserInfoResponse.class, id);
        } catch (Exception e) {
            logger.error("Got error while retrieving user {}", id, e);
            return null;
        }
    }

    /**
     * Registra un nuovo utente nella piattaforma
     *
     * @param dto i dati di registrazione del nuovo utente
     * @return true se la registrazione va a buon fine, false altrimenti
     */
    public boolean register(RegistrationDto dto) {
        final var query = "INSERT INTO user_identity (id, first_name, last_name, email, phone, propic) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(con -> {
                final var statement = con.prepareStatement(query);
                statement.setInt(1, dto.getId());
                statement.setString(2, dto.getFirstName());
                statement.setString(3, dto.getLastName());
                statement.setString(4, dto.getEmail());
                statement.setString(5, dto.getPhone());
                statement.setString(6, dto.getPropic());
                return statement;
            });
            return true;
        } catch (Exception e) {
            logger.error("Registration error", e);
            return false;
        }
    }

}
