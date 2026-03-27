package com.nihith.breakdown.model.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nihith.breakdown.model.exceptions.SystemException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapperUtil {

    public static final Logger logger = LogManager.getLogger(ObjectMapperUtil.class);

    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static Document castToDocument(Object pojo) {
        logger.info("Entered castToDocument");
        try {
            return Document.parse(objectMapper.writeValueAsString(pojo));
        } catch (JsonProcessingException e) {
            throw new SystemException("JSON Processing Exception Occurred");
        }
    }

    public static <T> T castToObject(Document document, Class<T> objectClass) {
        logger.info("Entered castToObject");
        try {
            logger.debug("Class to be casted::{}", objectClass);
            return objectMapper.readValue(document.toJson(), objectClass);
        } catch (JsonProcessingException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            throw new SystemException("JSON Processing Exception Occurred");
        }
    }

    public static <T> List<Document> castToDocumentList(List<T> pojoList) {

        logger.info("Entered castToDocumentList");
        List<Document> documentList = new ArrayList<>();
        try {
            documentList = pojoList.parallelStream().map(ObjectMapperUtil::castToDocument).toList();
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            throw new SystemException(e);
        }
        return documentList;
    }

}
