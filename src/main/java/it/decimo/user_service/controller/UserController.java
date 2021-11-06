package it.decimo.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.decimo.user_service.dto.RegistrationDto;
import it.decimo.user_service.dto.responses.BasicResponse;
import it.decimo.user_service.dto.responses.UserInfoResponse;
import it.decimo.user_service.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}/info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restituisce le informazioni dell'utente", content = @Content(schema = @Schema(implementation = UserInfoResponse.class))),
            @ApiResponse(responseCode = "404", description = "L'utente non è stato trovato", content = @Content(schema = @Schema(implementation = BasicResponse.class))) })
    public ResponseEntity<Object> getUserInfoById(@PathVariable("id") Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(new BasicResponse("Missing email", "NO_EMAIL_IN_REQUEST"));
        }
        return ResponseEntity.ok(userService.getUserInfo(id));
    }

    @PostMapping("/register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La registrazione è andata a buon fine", content = @Content(schema = @Schema(implementation = BasicResponse.class))),
            @ApiResponse(responseCode = "422", description = "La registrazione è fallita", content = @Content(schema = @Schema(implementation = BasicResponse.class))) })
    public ResponseEntity<Object> register(@RequestBody RegistrationDto body) {
        log.info("Registering new user with id {}", body.getId());
        final var registered = userService.register(body);
        if (registered) {
            return ResponseEntity.ok(new BasicResponse("Registration completed", "REGISTRATION_COMPLETED"));
        } else {
            return ResponseEntity.unprocessableEntity()
                    .body(new BasicResponse("Failed to register user", "REGISTRATION_FAILED"));
        }
    }
}
