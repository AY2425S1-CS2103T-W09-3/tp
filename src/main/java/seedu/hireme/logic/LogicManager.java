package seedu.hireme.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.hireme.commons.core.GuiSettings;
import seedu.hireme.commons.core.LogsCenter;
import seedu.hireme.logic.commands.Command;
import seedu.hireme.logic.commands.CommandResult;
import seedu.hireme.logic.commands.exceptions.CommandException;
import seedu.hireme.logic.parser.AddressBookParser;
import seedu.hireme.logic.parser.exceptions.ParseException;
import seedu.hireme.model.HireMeComparable;
import seedu.hireme.model.Model;
import seedu.hireme.model.ReadOnlyAddressBook;
import seedu.hireme.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager<T extends HireMeComparable<T>> implements Logic<T> {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model<T> model;
    private final Storage<T> storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model<T> model, Storage<T> storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command<T> command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook<T> getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<T> getFilteredList() {
        return model.getFilteredList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getHireMeFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}