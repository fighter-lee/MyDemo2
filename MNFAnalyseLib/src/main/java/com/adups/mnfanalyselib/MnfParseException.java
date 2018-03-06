package com.adups.mnfanalyselib;

/**
 * @author fighter_lee
 * @date 2018/1/21
 */
public class MnfParseException extends Exception {

    private String errorMsg;

    public MnfParseException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }

}
