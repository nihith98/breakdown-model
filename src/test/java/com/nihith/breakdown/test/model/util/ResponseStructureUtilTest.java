package com.nihith.breakdown.test.model.util;

import com.nihith.breakdown.model.response.MessageType;
import com.nihith.breakdown.model.response.ResponseStatus;
import com.nihith.breakdown.model.response.ResponseStructure;
import com.nihith.breakdown.model.util.ResponseStructureUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ResponseStructureUtil.class)
public class ResponseStructureUtilTest {

    @Test
    void generateResponseStructure_withPayload_success() {
        // Arrange
        Object payload = "Sample Payload";
        ResponseStatus status = ResponseStatus.SUCCESS;
        String message = "Information Message";
        MessageType messageType = MessageType.INFORMATION;
        String warningMessage = "Warning Message";

        // Act
        ResponseStructure result = ResponseStructureUtil.generateResponseStructure(payload, status, message, messageType, warningMessage);

        // Assert
        assertNotNull(result);
        assertEquals(payload, result.getPayload());
        assertEquals(status, result.getStatus());
        assertNotNull(result.getMessages());
        assertNotNull(result.getMessages().getInformationMessages());
        assertEquals(1, result.getMessages().getInformationMessages().size());
        assertEquals(message, result.getMessages().getInformationMessages().get(0));
        assertNotNull(result.getMessages().getWarningMessages());
        assertEquals(1, result.getMessages().getWarningMessages().size());
        assertEquals(warningMessage, result.getMessages().getWarningMessages().get(0));
        assertNull(result.getMessages().getErrorMessages());

    }


    @Test
    void generateResponseStructure_withPayload_failure_ERROR() {
        // Arrange
        Object payload = "Sample Payload";
        ResponseStatus status = ResponseStatus.FAILURE;
        String message = "Information Message";
        MessageType messageType = MessageType.ERROR;
        String warningMessage = "Warning Message";

        // Act
        ResponseStructure result = ResponseStructureUtil.generateResponseStructure(payload, status, message, messageType, warningMessage);

        // Assert
        assertNotNull(result);
        assertEquals(payload, result.getPayload());
        assertEquals(status, result.getStatus());
        assertNotNull(result.getMessages());
        assertNotNull(result.getMessages().getErrorMessages());
        assertEquals(1, result.getMessages().getErrorMessages().size());
        assertEquals(message, result.getMessages().getErrorMessages().get(0));
        assertNotNull(result.getMessages().getWarningMessages());
        assertEquals(1, result.getMessages().getWarningMessages().size());
        assertEquals(warningMessage, result.getMessages().getWarningMessages().get(0));
        assertNull(result.getMessages().getInformationMessages());

    }


    @Test
    void generateResponseStructure_withoutPayload_success() {
        // Arrange
        ResponseStatus status = ResponseStatus.FAILURE;
        String message = "Error Message";
        MessageType messageType = MessageType.ERROR;
        String warningMessage = null;

        // Act
        ResponseStructure result = ResponseStructureUtil.generateResponseStructure(null, status, message, messageType, warningMessage);

        // Assert
        assertNotNull(result);
        assertNull(result.getPayload());
        assertEquals(status, result.getStatus());
        assertNotNull(result.getMessages());
        assertNotNull(result.getMessages().getErrorMessages());
        assertEquals(1, result.getMessages().getErrorMessages().size());
        assertEquals(message, result.getMessages().getErrorMessages().get(0));
        assertNull(result.getMessages().getWarningMessages());
        assertNull(result.getMessages().getInformationMessages());
    }

    @Test
    void generateResponseStructure_withEmptyWarningMessage_success() {
        // Arrange
        Object payload = "Payload";
        ResponseStatus status = ResponseStatus.SUCCESS;
        String message = "Info Message";
        MessageType messageType = MessageType.INFORMATION;
        String warningMessage = "";

        //Act
        ResponseStructure result = ResponseStructureUtil.generateResponseStructure(payload, status, message, messageType, warningMessage);
        //Assert
        assertNotNull(result);
        assertEquals(payload, result.getPayload());
        assertEquals(status, result.getStatus());
        assertNotNull(result.getMessages());
        assertNotNull(result.getMessages().getInformationMessages());
        assertEquals(1, result.getMessages().getInformationMessages().size());
        assertEquals(message, result.getMessages().getInformationMessages().get(0));
        assertNull(result.getMessages().getWarningMessages());
    }


    //Mock static dependencies
    @Test
    void generateResponseStructure_withBlankWarningMessage_success() {
        // Arrange
        Object payload = "Payload";
        ResponseStatus status = ResponseStatus.SUCCESS;
        String message = "Info Message";
        MessageType messageType = MessageType.INFORMATION;

        try (MockedStatic<StringUtils> mockedStatic = Mockito.mockStatic(StringUtils.class)) {
            mockedStatic.when(() -> StringUtils.isNotBlank(any())).thenReturn(false);
            //Act
            ResponseStructure result = ResponseStructureUtil.generateResponseStructure(payload, status, message, messageType, null);
            //Assert
            assertNotNull(result);
            assertEquals(payload, result.getPayload());
            assertEquals(status, result.getStatus());
            assertNotNull(result.getMessages());
            assertNotNull(result.getMessages().getInformationMessages());
            assertEquals(1, result.getMessages().getInformationMessages().size());
            assertEquals(message, result.getMessages().getInformationMessages().get(0));
            assertNull(result.getMessages().getWarningMessages());
            //mockedStatic.verify(()-> StringUtils.isNotBlank(any()), times(1));
        }


    }


}
