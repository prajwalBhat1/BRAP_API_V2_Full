package com.brap.common.response;

import java.io.Serializable;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ErrorMessage implements Serializable {

    public static final String INVALID_MESSAGE = "[%s] is a required field";

    private ErrorCode errorCode;
    private String errorMessage;
    private String userErrorMessage;
    private String errorIdentifier;


    public ErrorMessage() {
        // Default constructor.
    }


    public ErrorMessage(String errorMessage, String errorIdentifier) {
        this.errorMessage = errorMessage;
        this.errorIdentifier = errorIdentifier;
    }


    /**
     * Creates an errorMessage instance that contains the errorCode and the associated
     * error message found in the appropriate Resource Bundle.
     * @param errorCode
     */
    //TODO: need to implement resource bundle look up.
    public ErrorMessage(ErrorCode errorCode) {
        setErrorCode(errorCode);
        this.errorMessage = "Current not supported";
        this.errorIdentifier = UUID.randomUUID().toString();
    }

    /**
     * Used in situations where the errorCode cannot be used to retrieve the
     * desired message from the Resource Bundle.
     *
     * @param errorCode
     * @param errorMessage
     */
    public ErrorMessage(ErrorCode errorCode, String errorMessage) {
        setErrorCode(errorCode);
        this.errorMessage = errorMessage;
        this.userErrorMessage = StringUtils.EMPTY;
        this.errorIdentifier = UUID.randomUUID().toString();
    }

    /**
     * Used in situations where the errorCode cannot be used to retrieve the
     * desired error and user error messages from the Resource Bundle.
     *
     * @param errorCode
     * @param errorMessage
     */
    public ErrorMessage(ErrorCode errorCode, String errorMessage, String userErrorMessage) {
        setErrorCode(errorCode);
        this.errorMessage = errorMessage;
        this.userErrorMessage = userErrorMessage;
        this.errorIdentifier = UUID.randomUUID().toString();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    private void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode != null ? errorCode: ErrorCode.UNKNOWN;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getUserErrorMessage() {
        return userErrorMessage;
    }

    public String getErrorIdentifier() {
        return errorIdentifier;
    }

    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(ToStringStyle.DEFAULT_STYLE);
        toStringBuilder.append("errorCodeEnum", errorCode);
        toStringBuilder.append("errorMessage", errorMessage);
        toStringBuilder.append("userErrorMessage", errorMessage);
        toStringBuilder.append("errorIdentifier", errorIdentifier);

        return toStringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ErrorMessage)) {
            return false;
        }

        ErrorMessage that = (ErrorMessage) o;

        return new EqualsBuilder().append(errorCode, that.errorCode).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                        .append(errorCode).toHashCode();
    }
}

