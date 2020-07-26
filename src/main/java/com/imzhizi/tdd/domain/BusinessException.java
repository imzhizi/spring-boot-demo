package com.imzhizi.tdd.domain;

/**
 * created by zhizi
 * on 7/24/20 15:21
 */
public class BusinessException extends Exception {
    private CodeMsg codeMsg;

    public BusinessException(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;
    }
}
