package com.godmonth.topia.basic.exception.i18n;

import com.godmonth.topia.basic.exception.ErrorCodeException;
import com.godmonth.topia.rpc.i18n.I18nMessage;
import lombok.Getter;

/**
 * <p></p >
 *
 * @author shenyue
 */
public class I18nErrorCodeException extends ErrorCodeException {
    @Getter
    private I18nMessage i18nMessage;

    public I18nErrorCodeException(String errorCode, String message, I18nMessage i18nMessage) {
        super(errorCode, message);
        this.i18nMessage=i18nMessage;
    }

}
