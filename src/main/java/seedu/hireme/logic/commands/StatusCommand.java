package seedu.hireme.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.hireme.logic.Messages.MESSAGE_INDEX_CONSTRAINT;
import static seedu.hireme.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
import seedu.hireme.commons.core.index.Index;
import seedu.hireme.logic.Messages;
import seedu.hireme.logic.commands.exceptions.CommandException;
import seedu.hireme.model.Model;
import seedu.hireme.model.internshipapplication.InternshipApplication;
import seedu.hireme.model.internshipapplication.Status;

/**
 * Changes the status of an internship application identified using its displayed index.
 */
public class StatusCommand extends Command {

    /** Command word for accepting an application. */
    public static final String COMMAND_WORD_ACCEPT = "/accept";

    /** Command word for marking an application as pending. */
    public static final String COMMAND_WORD_PENDING = "/pending";

    /** Command word for rejecting an application. */
    public static final String COMMAND_WORD_REJECT = "/reject";

    /**
     * Message to display for command usage instructions.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD_ACCEPT
            + " or " + COMMAND_WORD_PENDING + " or " + COMMAND_WORD_REJECT
            + ": Changes the status of the internship application identified by "
            + "the index number used in the displayed list.\n"
            + "Parameters: INDEX (" + MESSAGE_INDEX_CONSTRAINT + ")\n"
            + "Example: " + COMMAND_WORD_ACCEPT + " 5" + " (if total number of applications is <= 5)";

    /**
     * Message to display upon successful status update.
     */
    public static final String MESSAGE_STATUS_CHANGE_SUCCESS = "Updated status of internship application: %1$s to %2$s";

    private final Index targetIndex;
    private final Status newStatus;

    /**
     * Constructs a {@code StatusCommand}.
     *
     * @param targetIndex The index of the internship application to update.
     * @param newStatus The new status to set for the internship application.
     */
    public StatusCommand(Index targetIndex, Status newStatus) {
        this.targetIndex = targetIndex;
        this.newStatus = newStatus;
    }

    /**
     * Executes the command to update the status of an internship application.
     *
     * @param model The model containing the list of internship applications.
     * @return A {@code CommandResult} indicating the result of the status update.
     * @throws CommandException If the target index is invalid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        FilteredList<InternshipApplication> lastShownList =
                (FilteredList<InternshipApplication>) model.getFilteredList();
        @SuppressWarnings("unchecked")
        Predicate<InternshipApplication> prevPredicate = lastShownList.getPredicate() == null
                ? Model.PREDICATE_SHOW_ALL : (Predicate<InternshipApplication>) lastShownList.getPredicate();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        InternshipApplication internshipApplicationToUpdate = lastShownList.get(targetIndex.getZeroBased());
        InternshipApplication updatedInternshipApplication = internshipApplicationToUpdate.deepCopy();
        updatedInternshipApplication.setStatus(newStatus);
        model.setItem(internshipApplicationToUpdate, updatedInternshipApplication);
        model.updateFilteredList(prevPredicate);

        return new CommandResult(String.format(MESSAGE_STATUS_CHANGE_SUCCESS,
                Messages.format(internshipApplicationToUpdate), newStatus.toString()), false,
                false, false, model.getChartData());
    }


    /**
     * Checks if this command is equal to another object.
     *
     * @param other The other object to compare.
     * @return True if both objects are the same or have the same target index and new status, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof StatusCommand)) {
            return false;
        }

        StatusCommand otherStatusCommand = (StatusCommand) other;
        return targetIndex.equals(otherStatusCommand.targetIndex)
                && newStatus.equals(otherStatusCommand.newStatus);
    }

    /**
     * Returns the string representation of this command.
     *
     * @return A string representing this {@code StatusCommand}.
     */
    @Override
    public String toString() {
        return StatusCommand.class.getCanonicalName()
                + "{targetIndex=" + targetIndex + ", newStatus=" + newStatus.toString() + "}";
    }
}
