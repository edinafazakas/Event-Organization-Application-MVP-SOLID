package DB;
import Model.*;
import Model.Persistence.*;
import com.sun.jdi.LongValue;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CreateDB {

    AdminPersistence adminPersistence;
    ClientPersistence clientPersistence;
    EventCoordPersistence eventCoordPersistence;
    EventsPersistence eventsPersistence;
    UserPersistence userPersistence;

    CreateDB(){
        adminPersistence = new AdminPersistence();
        clientPersistence = new ClientPersistence();
        eventsPersistence = new EventsPersistence();
        eventCoordPersistence = new EventCoordPersistence();
        userPersistence = new UserPersistence();
    }

    public void createDB(){

        /*Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }*/

        /*User user1= new User("Popescu Ghita", "123546","a3bcd@yahoo.com","admin","ghita123","1234");
        User user2 = new User("Gheorghescu Gheorghita", "123546","abcde@yahoo.com","client","gheor123","1234");
        User user3 = new User("Marin Lica", "123546","abcdef@yahoo.com","eventCoord","lica123","1234");
            Admin admin=new Admin(user1);
            Client client=new Client(user2);
            EventCoord eventCoord = new EventCoord(user3);
        adminPersistence.createAdmin(admin);
        clientPersistence.createClient(client);
        eventCoordPersistence.createCoord(eventCoord);

        User user4 = new User("Dobosan Flavius", "123546","kgfjbaed@yahoo.com","client","flavius123","1234");
        Client client1 = new Client(user4);
        clientPersistence.createClient(client1);

        User user6 = new User("Dragos Lili", "123546","vadbjs@yahoo.com","eventCoord","lili123","1234");
        EventCoord eventCoord2 = new EventCoord(user6);
        eventCoordPersistence.createCoord(eventCoord2);*/
        Events events = new Events("nunta", "Cluj-Napoca", "200", "2023-08-09");
        Events events1 = new Events("absolvire", "Floresti", "50", "2023-07-10");
        Events events2 = new Events("nunta", "Sibiu", "180", "2023-07-18");
        Events events3 = new Events("concert", "Bucuresti", "1000", "2024-01-10");
        Events events4 = new Events("nunta", "Cluj-Napoca", "300", "2022-09-11");

        eventsPersistence.createEvents(events);
        eventsPersistence.createEvents(events1);
        eventsPersistence.createEvents(events2);
        eventsPersistence.createEvents(events3);
        eventsPersistence.createEvents(events4);

        //clientPersistence.deleteClient(36L);
        //userPersistence.deleteUser(String.valueOf(Long.parseLong("40")));

    }
    public static void main(String[] args) {

        CreateDB createDB = new CreateDB();
        createDB.createDB();

    }
}
