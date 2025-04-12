package com.nihith.breakdown.model.util;

import com.nihith.breakdown.model.response.MessageType;
import com.nihith.breakdown.model.response.ResponseMessages;
import com.nihith.breakdown.model.response.ResponseStatus;
import com.nihith.breakdown.model.response.ResponseStructure;
import io.micrometer.common.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Response structure util.
 */
public class ResponseStructureUtil {

    /**
     * The constant logger.
     */
    public final static Logger logger = LogManager.getLogger(ResponseStructureUtil.class);

    /**
     * Generate response structure response structure.
     *
     * @param response       the response
     * @param status         the status
     * @param message        the message
     * @param messageType    the message type
     * @param warningMessage the warning message
     * @return the response structure
     */
    public static ResponseStructure generateResponseStructure(Object response, ResponseStatus status, String message, MessageType messageType, String warningMessage) {
        ResponseStructure responseStructure = new ResponseStructure();
        if (null != response) {
            responseStructure.setPayload(response);
        }
        responseStructure.setStatus(status);
        ResponseMessages responseMessages = new ResponseMessages();
        switch (messageType) {
            case INFORMATION:
                responseMessages.setInformationMessages(List.of(message));
                break;
            case ERROR:
                responseMessages.setErrorMessages(List.of(message));
                break;
        }
        responseStructure.setMessages(responseMessages);
        if (StringUtils.isNotBlank(warningMessage)) {
            responseMessages.setWarningMessages(List.of(warningMessage));
        }
        logger.debug("Response Structure Generated::{}", responseStructure.toString());
        return responseStructure;
    }


}
