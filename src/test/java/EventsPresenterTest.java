import Model.Events;
import Presenter.EventsPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventsPresenterTest {

    private EventsPresenter presenter;

    @BeforeEach
    public void setUp() {
        presenter = new EventsPresenter();
    }

    @Test
    public void testAllEvents() {
        List<Events> expectedEvents = new ArrayList<>();
        expectedEvents.add(new Events("Scop11", "Locatie11", "Data11", "100"));

        List<Events> actualEvents = presenter.allEvents();

        for (int i = 0; i < expectedEvents.size(); i++) {
            assertEquals(expectedEvents.get(i).getScop(), actualEvents.get(i).getScop());
            assertEquals(expectedEvents.get(i).getLocatie(), actualEvents.get(i).getLocatie());
            assertEquals(expectedEvents.get(i).getData(), actualEvents.get(i).getData());
            assertEquals(expectedEvents.get(i).getNumarPersoane(), actualEvents.get(i).getNumarPersoane());
        }
    }

    @Test
    public void testCreateEvent() {
        Events newEvent = new Events("event5", "locatie6", "data5", "200");
        presenter.createEvent(newEvent);
        List<Events> actualEvents = presenter.allEvents();
        assertTrue(actualEvents.contains(newEvent));
    }

    @Test
    public void testDeleteEvent() {
        List<Events> eventsBeforeDeletion = presenter.allEvents();

        presenter.deleteEvent(eventsBeforeDeletion.get(0).getIdEvent());

        List<Events> eventsAfterDeletion = presenter.allEvents();

        assertEquals(eventsBeforeDeletion.size() - 1, eventsAfterDeletion.size());
    }

    @Test
    public void testModifyEvent() {
        List<Events> eventsBeforeModification = presenter.allEvents();

        Events eventToModify = eventsBeforeModification.get(0);
        Long id = eventToModify.getIdEvent();
        String newName = "New Event Name";
        String  newStartDate = "new date";
        String newLocation = "New Location";
        String nrPersons = "400";
        presenter.modifyEvent(id, newName, newLocation, newStartDate, nrPersons);

        Events modifiedEvent = presenter.findEvent(id);

        assertEquals(newName, modifiedEvent.getScop());
        assertEquals(newStartDate, modifiedEvent.getData());
        assertEquals(newLocation, modifiedEvent.getLocatie());
        assertEquals(nrPersons, modifiedEvent.getNumarPersoane());
    }

    @Test
    public void testFindEvent() {
        List<Events> events = presenter.allEvents();
        Events expectedEvent = events.get(0);

        Events actualEvent = presenter.findEvent(expectedEvent.getIdEvent());

        assertEquals(expectedEvent.getScop(), actualEvent.getScop());
        assertEquals(expectedEvent.getLocatie(), actualEvent.getLocatie());
        assertEquals(expectedEvent.getData(), actualEvent.getData());
        assertEquals(expectedEvent.getNumarPersoane(), actualEvent.getNumarPersoane());
    }
}