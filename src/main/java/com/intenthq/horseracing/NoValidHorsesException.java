package com.intenthq.horseracing;

public class NoValidHorsesException extends Exception implements HorseRacingInputException {
    private String code;

    public NoValidHorsesException(String code, String message) {
        super(message);
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return this.getMessage();
    }
}
