package seedu.hireme.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.hireme.commons.exceptions.DataLoadingException;
import seedu.hireme.model.HireMeComparable;
import seedu.hireme.model.ReadOnlyAddressBook;
import seedu.hireme.model.ReadOnlyUserPrefs;
import seedu.hireme.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage<T extends HireMeComparable<T>> extends AddressBookStorage<T>, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook<T>> readAddressBook() throws DataLoadingException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook<T> addressBook) throws IOException;

}