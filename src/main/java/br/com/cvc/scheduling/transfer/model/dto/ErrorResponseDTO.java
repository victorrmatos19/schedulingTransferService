package br.com.cvc.scheduling.transfer.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Error Response model
 *
 * @author Victor Rodrigues de Matos
 */

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDTO implements Serializable {
    private static final long serialVersionUID = 653403515228815159L;
    private static final String SEPARATOR = "[#]";

    @JsonIgnore
    private HttpStatus httpStatus;
    private String field;
    private String code;
    private String message;

    @Override
    public String toString() {
        return code
                .concat(SEPARATOR)
                .concat(message);
    }
}
