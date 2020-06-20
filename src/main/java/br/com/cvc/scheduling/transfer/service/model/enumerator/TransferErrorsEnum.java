package br.com.cvc.scheduling.transfer.service.model.enumerator;

public enum TransferErrorsEnum {

    GENERIC_ERROR("STS-001", "Generic error in application."),
    INVALID_DATE("STS-002", "Transfer date less than current date."),
    TRANSFER_VALUE_IS_ZERO("STS-003", "Transfer value is zero."),
    ACCOUNTS_NUMBER_ARE_EQUALS("STS-004", "SourceAccount and TargetAccount are the Same.");

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
