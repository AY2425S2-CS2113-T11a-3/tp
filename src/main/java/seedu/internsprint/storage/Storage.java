package seedu.internsprint.storage;

import seedu.internsprint.logic.command.CommandResult;

import java.io.IOException;

/**
 * Interface for storage classes.
 *
 * @param <T> The type of data to be stored.
 */
public interface Storage<T> {

    public void createFile();

    public void save(T data) throws IOException;

    public CommandResult load(T data);
}
