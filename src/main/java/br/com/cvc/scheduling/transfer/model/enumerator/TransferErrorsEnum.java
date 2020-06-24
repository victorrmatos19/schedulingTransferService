package br.com.cvc.scheduling.transfer.model.enumerator;

/**
 * Enum to Error Handling
 *
 * @author Victor Rodrigues de Matos
 */
public enum TransferErrorsEnum {

    GENERIC_ERROR("STS-001", "Generic error in application."),
    INVALID_DATE("STS-002", "Transfer date less than current date."),
    TRANSFER_VALUE_IS_ZERO("STS-003", "Transfer value is zero."),
    ACCOUNTS_NUMBER_ARE_EQUALS("STS-004", "SourceAccount and TargetAccount are the Same."),
    RATE_NOT_FOUND("STS-005", "Rate not found."),
    RATES_NOT_FOUND("STS-006", "Rates not found on database."),
    VALUE_NOT_VALID_FOR_RATE("STS-007", "Value not valid for this rate.");

    private final String code;
    private final String description;

    TransferErrorsEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
