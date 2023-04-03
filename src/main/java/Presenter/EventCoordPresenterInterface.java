package Presenter;

import Model.Client;
import Model.EventCoord;
import Model.Events;
import Model.User;

import java.util.List;

public interface EventCoordPresenterInterface {
    public List<EventCoord> allEventCoord();
    public void createEventCoord(User user);
    public void deleteEventCoord(Long id);
    public void modifyEventCoord(Long id, String name, String phone, String mail, String username, String password);
    public EventCoord findEventCoord(Long id);

}
