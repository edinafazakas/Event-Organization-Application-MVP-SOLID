package View;

import Model.EventCoord;

import java.util.List;

public interface ViewEventCoordViewInterface {

    public List<EventCoord> getAllEventCoord();
    public void getTableContents(List<EventCoord> eventCoords);
    public void tableSelection();
}
