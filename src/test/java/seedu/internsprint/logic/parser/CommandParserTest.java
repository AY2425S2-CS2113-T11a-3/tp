package seedu.internsprint.logic.parser;

import seedu.internsprint.logic.command.internship.AddSoftwareInternshipCommand;
import seedu.internsprint.logic.command.Command;
import seedu.internsprint.model.internship.InternshipList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandParserTest {

    @Test
    void parseKeyValuePairs_emptyInput_returnsNull() {
        Command command = new AddSoftwareInternshipCommand();
        CommandParser.parseKeyValuePairs("", command);
        assertEquals(0, command.getParameters().size());
    }

    @Test
    void parseKeyValuePairs_singleKeySingleWord_returnsCorrectMap() {
        Command command = new AddSoftwareInternshipCommand();
        CommandParser.parseKeyValuePairs("/key1 value1", command);
        assertEquals(1, command.getParameters().size());
        assertEquals("value1", command.getParameters().get("/key1"));
    }

    @Test
    void parseKeyValuePairs_singleKeyMultipleWords_returnsCorrectMap() {
        Command command = new AddSoftwareInternshipCommand();
        CommandParser.parseKeyValuePairs("/key1 value1 value2", command);
        assertEquals(1, command.getParameters().size());
        assertEquals("value1 value2", command.getParameters().get("/key1"));
    }

    @Test
    void parseKeyValuePairs_multipleKeysSingleWord_returnsCorrectMap() {
        Command command = new AddSoftwareInternshipCommand();
        CommandParser.parseKeyValuePairs("/key1 value1 /key2 value2", command);
        assertEquals(2, command.getParameters().size());
        assertEquals("value1", command.getParameters().get("/key1"));
        assertEquals("value2", command.getParameters().get("/key2"));
    }

    @Test
    void parseKeyValuePairs_multipleKeysMultipleWords_returnsCorrectMap() {
        Command command = new AddSoftwareInternshipCommand();
        CommandParser.parseKeyValuePairs("/key1 value1 value2 /key2 value3", command);
        assertEquals(2, command.getParameters().size());
        assertEquals("value1 value2", command.getParameters().get("/key1"));
        assertEquals("value3", command.getParameters().get("/key2"));
    }

    @Test
    void parseKeyValuePairs_singleKeyWithDescription_returnsCorrectMap() {
        Command command = new AddSoftwareInternshipCommand();
        CommandParser.parseKeyValuePairs("description /key1 value1", command);
        assertEquals(2, command.getParameters().size());
        assertEquals("description", command.getParameters().get("description"));
        assertEquals("value1", command.getParameters().get("/key1"));
    }

    @Test
    void parseKeyValuePairs_singleKeyWithMissingValue_throwsIllegalArgumentException() {
        Command command = new AddSoftwareInternshipCommand();
        assertThrows(IllegalArgumentException.class,
            () -> CommandParser.parseKeyValuePairs("/key1", command));
    }

    @Test
    void parseKeyValuePairs_singleKeyWithDescriptionAndMissingValue_throwsIllegalArgumentException() {
        Command command = new AddSoftwareInternshipCommand();
        assertThrows(IllegalArgumentException.class,
            () -> CommandParser.parseKeyValuePairs("description /key1", command));
    }

    @Test
    void parseKeyValuePairs_singleKeyWithInvalidValue_throwsIllegalArgumentException() {
        Command command = new AddSoftwareInternshipCommand();
        assertThrows(IllegalArgumentException.class,
            () -> CommandParser.parseKeyValuePairs("/key1 /value1", command));
    }

    @Test
    void parseKeyValuePairs_singleKeyWithInvalidValue2_throwsIllegalArgumentException() {
        Command command = new AddSoftwareInternshipCommand();
        assertThrows(IllegalArgumentException.class,
            () -> CommandParser.parseKeyValuePairs("description /key1 val/ue1", command));
    }

    @Test
    void parseKeyValuePairs_provideRepeatedFlag_throwsIllegalArgumentException() {
        Command command = new AddSoftwareInternshipCommand();
        assertThrows(IllegalArgumentException.class,
            () -> CommandParser.parseKeyValuePairs("/key1 value1 /key1 value2", command));
    }

    @Test
    void validateIndex_emptyIndex_throwsIllegalArgumentException() {
        InternshipList internships = new InternshipList();
        assertThrows(IllegalArgumentException.class,
            () -> CommandParser.validateIndex("", internships));
    }

    @Test
    void validateIndex_invalidIndex_throwsIllegalArgumentException() {
        InternshipList internships = new InternshipList();
        assertThrows(IllegalArgumentException.class,
            () -> CommandParser.validateIndex("0", internships));
    }
}
