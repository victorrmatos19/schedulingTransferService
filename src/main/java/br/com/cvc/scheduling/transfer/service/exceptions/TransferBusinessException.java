package br.com.cvc.scheduling.transfer.service.exceptions;

import br.com.cvc.scheduling.transfer.service.model.dto.ErrorResponseDTO;
import br.com.cvc.scheduling.transfer.service.model.enumerator.TransferErrorsEnum;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class TransferBusinessException extends RuntimeException {

    private ErrorResponseDTO errorResponse;

    public TransferBusinessException(HttpStatus httpStatus, TransferErrorsEnum transferErrorsEnum) {
        this.setErrorResponse(createErrorResponseDTO(httpStatus,transferErrorsEnum));
    }

    /**
     * Overrides the getter
     *
     * @return error response in string format
     */
    @Override
    public String getMessage() {
        return this.getErrorResponse().getMessage().toString();
    }

    private ErrorResponseDTO createErrorResponseDTO(HttpStatus httpStatus, TransferErrorsEnum transferErrorsEnum){
        return ErrorResponseDTO.builder()
                    .httpStatus(httpStatus)
                    .message(transferErrorsEnum.getDescription())
                    .code(transferErrorsEnum.getCode())
                .build();
    }
}
