package it.decimo.user_service.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class BasicResponse {
    private String message;
    private String code;
}
