package com.godmonth.topia.rpc.error.i18n;

import com.godmonth.topia.rpc.error.ErrorCodeImpl;

public class I18nErrorCodeImpl extends ErrorCodeImpl implements I18nErrorCode {

    private String messageKey;

    public I18nErrorCodeImpl(String code, String template, String messageKey) {
        super(code, template);
        this.messageKey = messageKey;
    }

    @Override
    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }
}
