package it.decimo.user_service.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.decimo.user_service.dto.RegistrationDto;
import it.decimo.user_service.dto.responses.BasicResponse;
import it.decimo.user_service.dto.responses.UserInfoResponse;
import it.decimo.user_service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}/info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restituisce le informazioni dell'utente", content = @Content(schema = @Schema(implementation = UserInfoResponse.class))),
            @ApiResponse(responseCode = "404", description = "L'utente non è stato trovato", content = @Content(schema = @Schema(implementation = BasicResponse.class)))
    })
    public ResponseEntity<Object> getUserInfoById(@PathVariable("id") Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(new BasicResponse("Missing email", "NO_EMAIL_IN_REQUEST"));
        }
        return ResponseEntity.ok(userService.getUserInfo(id));
    }

    @PostMapping("/register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La registrazione è andata a buon fine", content = @Content(schema = @Schema(implementation = BasicResponse.class))),
            @ApiResponse(responseCode = "422", description = "La registrazione è fallita", content = @Content(schema = @Schema(implementation = BasicResponse.class)))
    })
    public ResponseEntity<Object> register(@RequestBody RegistrationDto body) {
        logger.info("Registering new user with id {}", body.getId());
        final var registered = userService.register(body);
        if (registered) {
            return ResponseEntity.ok(new BasicResponse("Registration completed", "REGISTRATION_COMPLETED"));
        } else {
            return ResponseEntity.unprocessableEntity().body(new BasicResponse("Failed to register user", "REGISTRATION_FAILED"));
        }
    }
}
