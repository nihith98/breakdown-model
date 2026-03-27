package com.nihith.breakdown.test.model.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nihith.breakdown.model.exceptions.SystemException;
import com.nihith.breakdown.model.util.ObjectMapperUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.nihith.breakdown.model.util.ObjectMapperUtil.castToDocumentList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectMapperUtil.class)
class ObjectMapperUtilTest {

    private ObjectMapperUtil objectMapperUtil;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapperUtil = new ObjectMapperUtil(); // Assuming default constructor or a static utility class
    }

    @Test
    void testObjectToJson_validObject() {
        MyTestObject testObject = new MyTestObject("Test Name", 123);
        Document doc = ObjectMapperUtil.castToDocument(testObject);
        assertNotNull(doc);
        try {
            MyTestObject parsedObject = objectMapper.readValue(doc.toJson(), MyTestObject.class);
            assertEquals(testObject.getName(), parsedObject.getName());
            assertEquals(testObject.getValue(), parsedObject.getValue());
        } catch (Exception e) {
            fail("Exception during parsing json back to object: " + e.getMessage());
        }
    }

    @Test
    void testJsonToObject_validJson() {
        String json = "{\"name\":\"Test Name\",\"value\":456}";

        MyTestObject testObject = ObjectMapperUtil.castToObject(Document.parse(json), MyTestObject.class);
        assertNotNull(testObject);
        assertEquals("Test Name", testObject.getName());
        assertEquals(456, testObject.getValue());
    }

    @Test
    void testJsonToObject_invalidJson() {
        String json = "{\"name\":\"Test Name\",\"value\":\"invalid\"}";
        assertThrows(SystemException.class, () -> ObjectMapperUtil.castToObject(Document.parse(json), MyTestObject.class));
    }

    @Test
    void testJsonToObject_nullJson() {

        assertThrows(NullPointerException.class, () -> ObjectMapperUtil.castToObject(null, MyTestObject.class));
    }



    @Test
    void testCastToDocumentList_emptyList() {
        // Arrange
        List<String> pojoList = List.of();
        List<Document> expectedDocumentList = List.of();

        // Act
        List<Document> actualDocumentList = castToDocumentList(pojoList);

        // Assert
        assertEquals(expectedDocumentList, actualDocumentList);
    }




    // Example Test Object for testing
    public static class MyTestObject {
        private String name;
        private int value;

        public MyTestObject() {
        }

        public MyTestObject(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}