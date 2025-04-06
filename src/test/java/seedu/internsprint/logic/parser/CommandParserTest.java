package seedu.internsprint.logic.parser;

import seedu.internsprint.logic.command.internship.AddSoftwareInternshipCommand;
import seedu.internsprint.logic.command.Command;
import seedu.internsprint.model.internship.InternshipList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandParserTest {

    @Test
    void splitCommandTypeAndParams_provideValidSingleWordCommand_splitCorrectly() {
        String userInput = "add /key1 value1 /key2 value2";
        String[] result = CommandParser.splitCommandTypeAndParams(userInput);
        assertEquals("add", result[0]);
        assertEquals("/key1 value1 /key2 value2", result[1]);
    }

    @Test
    void splitCommandTypeAndParams_provideValidMultiWordCommand_splitCorrectly() {
        String userInput = "add software /key1 value1 /key2 value2";
        String[] result = CommandParser.splitCommandTypeAndParams(userInput);
        assertEquals("add software", result[0]);
        assertEquals("/key1 value1 /key2 value2", result[1]);
    }

    @Test
    void splitCommandTypeAndParams_provideSingleWordCommandWithNoKey_splitCorrectly() {
        String userInput = "bye";
        String[] result = CommandParser.splitCommandTypeAndParams(userInput);
        assertEquals("bye", result[0]);
        assertEquals("", result[1]);
    }
  
    void parseCommand_provideCorrectCommand_returnsCorrectObject() {
        String input = "add software /key1 value1";
        Command command = CommandParser.parseCommand(input);
        assertEquals(AddSoftwareInternshipCommand.class, command.getClass());
    }

    @Test
    void parseCommand_provideTrailingWhitespace_returnsCorrectObject() {
        String input = "add software   /key1 value1";
        Command command = CommandParser.parseCommand(input);
        assertEquals(AddSoftwareInternshipCommand.class, command.getClass());
    }

    @Test
    void parseCommand_provideLeadingWhitespace_returnsCorrectObject() {
        String input = "   add software /key1 value1";
        Command command = CommandParser.parseCommand(input);
        assertEquals(AddSoftwareInternshipCommand.class, command.getClass());
    }

    @Test
    void parseCommand_provideIncorrectCommand_throwsIllegalArgumentException() {
        String input = "add invalid /key1 value1";
        assertThrows(IllegalArgumentException.class, () -> CommandParser.parseCommand(input));
    }

    @Test
    void parseCommand_provideTrailingInvalidInput_throwsIllegalArgumentException() {
        String input = "add softwareinvalid /key1 value1";
        assertThrows(IllegalArgumentException.class, () -> CommandParser.parseCommand(input));
    }

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
    void parseKeyValuePairs_onlyDescription_returnsCorrectMap() {
        Command command = new AddSoftwareInternshipCommand();
        CommandParser.parseKeyValuePairs("description", command);
        assertEquals(1, command.getParameters().size());
        assertEquals("description", command.getParameters().get("description"));
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

    @Test
    void validateIndex_stringDoesNotContainNumber_throwsIllegalArgumentException() {
        InternshipList internships = new InternshipList();
        assertThrows(IllegalArgumentException.class,
            () -> CommandParser.validateIndex("abc", internships));
    }
}
