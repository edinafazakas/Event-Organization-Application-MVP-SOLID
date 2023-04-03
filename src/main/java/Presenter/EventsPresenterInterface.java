package Presenter;

import Model.Client;
import Model.Events;
import Model.User;
import jdk.jfr.Event;

import java.util.List;

public interface EventsPresenterInterface {
    public List<Events> allEvents();
    public void createEvent(Events events);
    public void deleteEvent(Long id);
    public void modifyEvent(Long id, String scop, String locatie, String data, String nuPersoane);
    public List<Events> sortEvents(String sortAfter);
    public Events findEvent(Long id);
    public List<Events> filterEvents(String filterAfter, String name);

}
