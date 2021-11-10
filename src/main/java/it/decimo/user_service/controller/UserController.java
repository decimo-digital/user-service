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
import it.decimo.user_service.dto.responses.BasicResponse;
import it.decimo.user_service.model.AuthUser;
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
            @ApiResponse(responseCode = "200", description = "Restituisce le informazioni dell'utente", content = @Content(schema = @Schema(implementation = AuthUser.class))),
            @ApiResponse(responseCode = "404", description = "L'utente non è stato trovato", content = @Content(schema = @Schema(implementation = BasicResponse.class))) })
    public ResponseEntity<Object> getAuthUserById(@PathVariable("id") Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(new BasicResponse("Missing email", "NO_EMAIL_IN_REQUEST"));
        }
        log.info("Getting info of id {}", id);
        return ResponseEntity.ok(userService.getAuthUser(id));
    }
}
