package Presenter;

import Model.Client;
import Model.EventCoord;
import Model.Events;
import Model.Persistence.EventCoordPersistence;
import Model.Persistence.UserPersistence;
import Model.User;

import java.util.List;

public class EventCoordPresenter implements EventCoordPresenterInterface{
    private EventCoordPersistence eventCoordPersistence;
    private UserPersistence userPersistence;

    public EventCoordPresenter(){
        this.eventCoordPersistence = new EventCoordPersistence();
        this.userPersistence = new UserPersistence();
    }

    public EventCoordPresenter(EventCoordPersistence eventCoordPersistence, UserPersistence userPersistence) {
        this.eventCoordPersistence = eventCoordPersistence;
        this.userPersistence = userPersistence;
    }

    @Override
    public List<EventCoord> allEventCoord() {
        List<EventCoord> eventCoords = eventCoordPersistence.getAllEventCoord();
        return eventCoords;
    }

    @Override
    public void createEventCoord(User user) {
        EventCoord eventCoord = new EventCoord(user);
        eventCoordPersistence.createCoord(eventCoord);
    }

    @Override
    public void deleteEventCoord(Long id) {
        eventCoordPersistence.deleteCoord(id);
    }

    @Override
    public void modifyEventCoord(Long id, String name, String phone, String mail, String username, String password) {
        System.out.println("\n" + id);
        EventCoord eventCoord  = eventCoordPersistence.getCoord(id);
        eventCoord.getUser().setName(name);
        eventCoord.getUser().setPhone(phone);
        eventCoord.getUser().setEmail(mail);
        eventCoord.getUser().setUsername(username);
        eventCoord.getUser().setPassword(password);
        eventCoordPersistence.updateCoord(eventCoord);
    }

    @Override
    public EventCoord findEventCoord(Long id) {
        EventCoord eventCoord = eventCoordPersistence.getCoord(id);
        return eventCoord;
    }
}
