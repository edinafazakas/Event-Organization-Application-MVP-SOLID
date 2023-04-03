package View;

import Model.Events;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public interface EventsViewInteface {

    public List<Events> getAllEvents();
    public void sortEvents();
    public void setTableContents(List<Events> events);
}
