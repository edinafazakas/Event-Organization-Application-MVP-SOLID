package Model.Persistence;
import Model.Client;
import Model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.module.Configuration;
import java.util.List;

public class UserPersistence {
    public void createUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the user object
            session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public User createUser1(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the user object
            session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the user object
            session.update(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteUser(String id) {

        Transaction transaction = null;
        //try (
        Session session = HibernateUtil.getSessionFactory().openSession();//) {
        // start a transaction
        transaction = session.beginTransaction();

        // Delete a user object
        User user = session.get(User.class, id);
        if (user != null) {
            session.delete(user);
            System.out.println("user is deleted");
        }

        // commit transaction
        transaction.commit();
        /*} catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }*/
    }

    public User getUser(Long id) {

        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get a user object
            user = session.get(User.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    public User getUserName(String username) {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        // start a transaction
        transaction = session.beginTransaction();
        // Create a criteria to search for a user with the specified username
        Criteria criteria = session.createCriteria(User.class);

        // Add the username as a search criterion
        criteria.add(Restrictions.eq("username", username));

        // Retrieve the user with the specified username
        User user = (User) criteria.uniqueResult();

        session.close();

        return user;
    }

    public String getEmail(String username) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);

        // Add the username as a search criterion
        criteria.add(Restrictions.eq("username", username));

        User user = (User) criteria.uniqueResult();
        session.close();
        return user.getEmail();
    }

    public List<User> findAllUser() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);

        TypedQuery<User> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public User findUserByUsername(String fieldValue) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("username", fieldValue));
        User user = (User) criteria.uniqueResult();
        session.close();
        session.close();

        return user;

    }

    public User getUserByClient(Client client) {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        // start a transaction
        transaction = session.beginTransaction();
        // Create a criteria to search for a user with the specified username
        Criteria criteria = session.createCriteria(User.class);

        // Add the username as a search criterion
        criteria.add(Restrictions.eq("client", client));

        // Retrieve the user with the specified username
        User user = (User) criteria.uniqueResult();

        session.close();

        return user;
    }

}
