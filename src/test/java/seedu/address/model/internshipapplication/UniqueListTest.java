package seedu.address.model.internshipapplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_YAHOO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_YAHOO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_YAHOO;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternshipApplications.GOOGLE;
import static seedu.address.testutil.TypicalInternshipApplications.YAHOO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.internshipapplication.exceptions.DuplicateInternshipException;
import seedu.address.model.internshipapplication.exceptions.InternshipNotFoundException;
import seedu.address.testutil.InternshipApplicationBuilder;

public class UniqueListTest {
    private final UniqueList<InternshipApplication> uniqueList = new UniqueList<>();

    @Test
    public void contains_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueList.contains(null));
    }

    @Test
    public void contains_internshipNotInList_returnsFalse() {
        assertFalse(uniqueList.contains(GOOGLE));
    }

    @Test
    public void contains_internshipInList_returnsTrue() {
        uniqueList.add(GOOGLE);
        assertTrue(uniqueList.contains(GOOGLE));
    }

    @Test
    public void contains_internshipWithSameIdentityFieldsInList_returnsTrue() {
        uniqueList.add(GOOGLE);
        InternshipApplication editedGoogle = new InternshipApplicationBuilder(GOOGLE)
                .withName(VALID_COMPANY_NAME_YAHOO)
                .withDate(VALID_DATE_YAHOO)
                .withRole(VALID_ROLE_YAHOO)
                .build();

        assertFalse(uniqueList.contains(editedGoogle));
    }

    @Test
    public void add_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueList.add(null));
    }

    @Test
    public void add_duplicateInternship_throwsDuplicateInternshipException() {
        uniqueList.add(GOOGLE);
        assertThrows(DuplicateInternshipException.class, () -> uniqueList.add(GOOGLE));
    }

    @Test
    public void setItem_nullTargetInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueList.setItem(null, GOOGLE));
    }

    @Test
    public void setItem_nullEditedInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueList.setItem(GOOGLE, null));
    }

    @Test
    public void setItem_targetInternshipNotInList_throwsInternshipNotFoundException() {
        assertThrows(InternshipNotFoundException.class, () -> uniqueList.setItem(GOOGLE, GOOGLE));
    }

    @Test
    public void setItem_editedInternshipIsSameInternship_success() {
        uniqueList.add(GOOGLE);
        uniqueList.setItem(GOOGLE, GOOGLE);
        UniqueList<InternshipApplication> expectedUniqueList = new UniqueList<>();
        expectedUniqueList.add(GOOGLE);
        assertEquals(expectedUniqueList, uniqueList);
    }

    @Test
    public void setItem_editedInternshipHasSameIdentity_success() {
        uniqueList.add(GOOGLE);
        InternshipApplication editedGoogle = new InternshipApplicationBuilder(GOOGLE)
                .withName(VALID_COMPANY_NAME_YAHOO)
                .withDate(VALID_DATE_YAHOO)
                .build();
        uniqueList.setItem(GOOGLE, editedGoogle);
        UniqueList<InternshipApplication> expectedUniqueList = new UniqueList<>();
        expectedUniqueList.add(editedGoogle);
        assertEquals(expectedUniqueList, uniqueList);
    }

    @Test
    public void setItem_editedInternshipHasDifferentIdentity_success() {
        uniqueList.add(GOOGLE);
        uniqueList.setItem(GOOGLE, YAHOO);
        UniqueList<InternshipApplication> expectedUniqueList = new UniqueList<>();
        expectedUniqueList.add(YAHOO);
        assertEquals(expectedUniqueList, uniqueList);
    }

    @Test
    public void setItem_editedInternshipHasNonUniqueIdentity_throwsDuplicateInternshipException() {
        uniqueList.add(GOOGLE);
        uniqueList.add(YAHOO);
        assertThrows(DuplicateInternshipException.class, () -> uniqueList.setItem(GOOGLE, YAHOO));
    }

    @Test
    public void remove_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueList.remove(null));
    }

    @Test
    public void remove_internshipDoesNotExist_throwsInternshipNotFoundException() {
        assertThrows(InternshipNotFoundException.class, () -> uniqueList.remove(GOOGLE));
    }

    @Test
    public void remove_existingInternship_removesInternship() {
        uniqueList.add(GOOGLE);
        uniqueList.remove(GOOGLE);
        UniqueList<InternshipApplication> expectedUniqueList = new UniqueList<>();
        assertEquals(expectedUniqueList, uniqueList);
    }

    @Test
    public void setItems_nullUniqueList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueList.setItems((UniqueList<InternshipApplication>) null));
    }

    @Test
    public void setItems_uniqueList_replacesOwnListWithProvidedUniqueList() {
        uniqueList.add(GOOGLE);
        UniqueList<InternshipApplication> expectedUniqueList = new UniqueList<>();
        expectedUniqueList.add(YAHOO);
        uniqueList.setItems(expectedUniqueList);
        assertEquals(expectedUniqueList, uniqueList);
    }

    @Test
    public void setItems_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueList.setItems((List<InternshipApplication>) null));
    }

    @Test
    public void setItems_list_replacesOwnListWithProvidedList() {
        uniqueList.add(GOOGLE);
        List<InternshipApplication> internshipList = Collections.singletonList(YAHOO);
        uniqueList.setItems(internshipList);
        UniqueList<InternshipApplication> expectedUniqueList = new UniqueList<>();
        expectedUniqueList.add(YAHOO);
        assertEquals(expectedUniqueList, uniqueList);
    }

    @Test
    public void setItems_listWithDuplicateInternships_throwsDuplicateInternshipException() {
        List<InternshipApplication> listWithDuplicateInternships = Arrays.asList(GOOGLE, GOOGLE);
        assertThrows(DuplicateInternshipException.class, () -> uniqueList.setItems(listWithDuplicateInternships));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueList.asUnmodifiableObservableList().toString(), uniqueList.toString());
    }
}
