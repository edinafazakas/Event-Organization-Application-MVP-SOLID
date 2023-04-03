package Presenter;

import Model.Events;
import Model.Persistence.EventsPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EventsPresenter implements EventsPresenterInterface {
    private EventsPersistence eventsPersistence;

    public EventsPresenter(EventsPersistence eventPersistence) {
        eventsPersistence = new EventsPersistence();
    }

    public EventsPresenter(){
        this.eventsPersistence = new EventsPersistence();
    }
    @Override
    public List<Events> allEvents() {
        List<Events> events = eventsPersistence.getAllEvents();
        return events;
    }

    @Override
    public void createEvent(Events events) {
        eventsPersistence.createEvents(events);
    }

    @Override
    public void deleteEvent(Long id) {
        eventsPersistence.deleteEvent(id);
    }

    @Override
    public void modifyEvent(Long id, String scop, String locatie, String data, String nrPersoane) {
        Events events = eventsPersistence.getEvent(id);
        events.setScop(scop);
        events.setLocatie(locatie);
        events.setData(data);
        events.setNumarPersoane(nrPersoane);
        eventsPersistence.updateEvent(events);
    }

    @Override
    public List<Events> sortEvents(String sortAfter) {
        List<Events> events = eventsPersistence.getAllEvents();

        if(sortAfter.equals("scop")){
            Collections.sort(events, new Comparator<Events>() {
                @Override
                public int compare(Events o1, Events o2) {
                    return o1.getScop().compareTo(o2.getScop());
                }

            });
        }
        else if(sortAfter.equals("locatie")){
            Collections.sort(events, new Comparator<Events>() {
                @Override
                public int compare(Events o1, Events o2) {
                    return o1.getLocatie().compareTo(o2.getLocatie());
                }

            });
        }
        return events;
    }

    @Override
    public Events findEvent(Long id) {
        Events events = eventsPersistence.getEvent(id);
        return events;
    }

    @Override
    public List<Events> filterEvents(String filterAfter, String name) {
        List<Events> events = eventsPersistence.getAllEvents();
        List<Events> returnedList = new ArrayList<>();

        if (filterAfter.equals("locatie")) {
            for (Events e : events) {
                if (e.getLocatie().equals(name)) {
                    returnedList.add(e);
                }
            }
        } else if (filterAfter.equals("numar persoane")) {
            for (Events e : events) {
                if (e.getNumarPersoane().equals(name)) {
                    returnedList.add(e);
                }
            }
        } else if (filterAfter.equals("scop")) {
            for (Events e : events) {
                if (e.getScop().equals(name)) {
                    returnedList.add(e);
                }
            }
        }
        return returnedList;
    }
}
