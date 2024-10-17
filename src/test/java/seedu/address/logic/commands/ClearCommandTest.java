package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternshipApplications.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.hireme.logic.commands.ClearCommand;
import seedu.hireme.model.AddressBook;
import seedu.hireme.model.Model;
import seedu.hireme.model.ModelManager;
import seedu.hireme.model.UserPrefs;
import seedu.hireme.model.internshipapplication.InternshipApplication;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model<InternshipApplication> model = new ModelManager<>();
        Model<InternshipApplication> expectedModel = new ModelManager<>();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model<InternshipApplication> model =
                new ModelManager<InternshipApplication>(getTypicalAddressBook(), new UserPrefs());
        Model<InternshipApplication> expectedModel =
                new ModelManager<InternshipApplication>(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook<>());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
