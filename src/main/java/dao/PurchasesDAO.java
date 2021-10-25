package dao;

import bo.Customer;
import bo.Purchases;
import bo.Ticket;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class PurchasesDAO {
    public List<Purchases> getAll() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<Purchases> purchases = session.createQuery(" from Purchases ").list();
            session.getTransaction().commit();
            return purchases;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    public List<Customer> sortByTotalTicket() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        String query= "select customer ,sum(quantity) from Purchases group by customer order by sum(quantity) asc";
        try {
            session.beginTransaction();
            List<Customer> purchases= session.createQuery(query).list();
            session.getTransaction().commit();
            return purchases;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }



    public List<Purchases> sortName() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<Purchases> purchases = session.createQuery(" from Purchases ORDER BY customer.name ").list();
            session.getTransaction().commit();
            return purchases;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    public static boolean addNew(Purchases purchases) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(purchases);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return false;
    }
    public static int checkQauntityTypeTicket(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query<Integer> query = session.createQuery("select count(ticket.id) from Purchases where customer.id=:p_id group by customer.id ");
            query.setParameter("p_id", id);
            int a = query.getSingleResult();
            session.getTransaction().commit();
            return a;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return 0;
    }
}
