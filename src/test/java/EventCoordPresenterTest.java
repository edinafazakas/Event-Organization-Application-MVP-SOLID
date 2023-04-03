import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import Model.EventCoord;
import Model.Persistence.EventCoordPersistence;
import Model.Persistence.UserPersistence;
import Model.User;
import Presenter.EventCoordPresenter;

public class EventCoordPresenterTest {

    @Mock
    private EventCoordPersistence eventCoordPersistence;

    @Mock
    private UserPersistence userPersistence;

    private EventCoordPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new EventCoordPresenter(eventCoordPersistence, userPersistence);
    }

    @Test
    public void testAllEventCoord() {
        List<EventCoord> eventCoords = new ArrayList<EventCoord>();
        eventCoords.add(new EventCoord(new User("coord1", "phone1", "mail1", "eventCoord", "username1", "password1")));
        eventCoords.add(new EventCoord(new User("coord2", "phone2", "mail2", "eventCoord", "username2", "password2")));

        when(eventCoordPersistence.getAllEventCoord()).thenReturn(eventCoords);

        List<EventCoord> result = presenter.allEventCoord();

        assertEquals(eventCoords, result);
    }

    @Test
    public void testCreateEventCoord() {
        User user = new User("coord1", "phone1", "kdjegblej", "eventCoord", "username1", "password1");
        EventCoord eventCoord = new EventCoord(user);
        /*when(eventCoordPersistence.createCoord(eventCoord)).thenReturn(eventCoord);
        presenter.createEventCoord(eventCoord);*/
        verify(eventCoordPersistence).createCoord(eventCoord);
    }

    @Test
    public void testDeleteEventCoord() {
        presenter.deleteEventCoord(1L);
        verify(eventCoordPersistence).deleteCoord(1L);
    }

    @Test
    public void testModifyEventCoord() {
        // create a user object
        User user = new User("coord1", "phone1", "mail1", "eventCoord", "username1", "password1");

        // create an eventCoord object
        EventCoord eventCoord = new EventCoord(user);

        // set up the mock behavior
        when(eventCoordPersistence.getCoord(1L)).thenReturn(eventCoord);

        // call the method to be tested
        presenter.modifyEventCoord(1L, "newName", "newPhone", "newMail", "newUsername", "newPassword");

        // verify that the updateCoord method of the eventCoordPersistence object was called with the correct argument
        verify(eventCoordPersistence).updateCoord(eventCoord);
        assertEquals("newName", user.getName());
        assertEquals("newPhone", user.getPhone());
        assertEquals("newMail", user.getEmail());
        assertEquals("newUsername", user.getUsername());
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    public void testFindEventCoord() {
        // create a user object
        User user = new User("coord1", "phone1", "mail1", "eventCoord", "username1", "password1");

        // create an eventCoord object
        EventCoord eventCoord = new EventCoord(user);

        // set up the mock behavior
        when(eventCoordPersistence.getCoord(1L)).thenReturn(eventCoord);

        // call the method to be tested
        EventCoord result = presenter.findEventCoord(1L);

        // verify the result
        assertEquals(eventCoord, result);
    }
}