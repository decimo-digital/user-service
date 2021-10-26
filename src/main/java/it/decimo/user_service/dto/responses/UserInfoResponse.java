package it.decimo.user_service.dto.responses;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserInfoResponse {
    private int id;
    @JsonAlias(value = "first_name")
    private String firstName;
    @JsonAlias(value = "last_name")
    private String lastName;
    private String email;
    private String phone;
    /**
     * Base64 dell'immagine di profilo
     */
    private String propic;
    /**
     * Il codice di referral univoco dell'utente
     */
    private String referral;
}
