package Model.Persistence;
import Model.Client;
import Model.EventCoord;
import Model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EventCoordPersistence {
    public void createCoord(EventCoord coord){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the client object
            session.save(coord);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void updateCoord(EventCoord coord) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the client object
            session.update(coord);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteCoord(Long id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a client object
            EventCoord coord = session.get(EventCoord.class, id);
            if (coord != null) {
                session.delete(coord);
                System.out.println("coordonator is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public EventCoord getCoord (Long id) {

        Transaction transaction = null;
        EventCoord coord = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get a client object
            coord = session.get(EventCoord.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return coord;
    }
    public EventCoord getCoordByUser (User user) {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        // start a transaction
        transaction = session.beginTransaction();
        // Create a criteria to search for a user with the specified username
        Criteria criteria = session.createCriteria(EventCoord.class);

        // Add the username as a search criterion
        criteria.add(Restrictions.eq("user", user));

        // Retrieve the user with the specified username
        EventCoord coord =(EventCoord) criteria.uniqueResult();

        session.close();

        return coord;
    }

    public List<EventCoord> getAllEventCoord(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<EventCoord> cq = cb.createQuery(EventCoord.class);
        Root<EventCoord> rootEntry = cq.from(EventCoord.class);
        CriteriaQuery<EventCoord> all = cq.select(rootEntry);

        TypedQuery<EventCoord> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }
}
