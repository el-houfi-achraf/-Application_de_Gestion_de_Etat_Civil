package ma.projet.service;

import ma.projet.beans.Mariage;
import ma.projet.beans.Personne;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class MariageService implements IDao<Mariage> {


    @Override
    public boolean create(Mariage mariage) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(mariage);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Mariage mariage) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(mariage);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Mariage mariage) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(mariage);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Mariage findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Mariage.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Mariage> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Mariage", Mariage.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Méthode pour afficher toutes les épouses d'un homme
    public List<Personne> findEpousesByHomme(int hommeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "SELECT DISTINCT m.femme FROM Mariage m WHERE m.homme.id = :hommeId",
                Personne.class
            ).setParameter("hommeId", hommeId)
             .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Méthode pour afficher les épouses d'un homme entre deux dates
    public List<Personne> findEpousesByHomme(int hommeId, Date dateDebut, Date dateFin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "SELECT m.femme FROM Mariage m WHERE m.homme.id = :hommeId " +
                "AND m.dateDebut BETWEEN :dateDebut AND :dateFin",
                Personne.class
            ).setParameter("hommeId", hommeId)
             .setParameter("dateDebut", dateDebut)
             .setParameter("dateFin", dateFin)
             .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Méthode pour compter les enfants d'une femme entre deux dates
    public int countEnfantsByFemmeAndDates(int femmeId, Date dateDebut, Date dateFin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long count = session.createQuery(
                "SELECT COALESCE(SUM(m.nbrEnfants), 0) FROM Mariage m " +
                "WHERE m.femme.id = :femmeId " +
                "AND m.dateDebut BETWEEN :dateDebut AND :dateFin",
                Long.class
            ).setParameter("femmeId", femmeId)
             .setParameter("dateDebut", dateDebut)
             .setParameter("dateFin", dateFin)
             .uniqueResult();
            return count != null ? count.intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Méthode pour afficher les femmes mariées deux fois ou plus
    public List<Personne> findFemmesMarieesPlusieuresFois() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "SELECT m.femme FROM Mariage m " +
                "GROUP BY m.femme " +
                "HAVING COUNT(m) >= 2",
                Personne.class
            ).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Méthode pour afficher les hommes mariés à quatre femmes entre deux dates
    public List<Personne> findHommesMariesByQuatreFemmes(Date dateDebut, Date dateFin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "SELECT m.homme FROM Mariage m " +
                "WHERE m.dateDebut BETWEEN :dateDebut AND :dateFin " +
                "GROUP BY m.homme " +
                "HAVING COUNT(DISTINCT m.femme) = 4",
                Personne.class
            ).setParameter("dateDebut", dateDebut)
             .setParameter("dateFin", dateFin)
             .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Méthode pour afficher les mariages d'un homme avec tous les détails
    public List<Mariage> findMariagesByHomme(int hommeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Mariage m WHERE m.homme.id = :hommeId ORDER BY m.dateFin DESC NULLS FIRST",
                Mariage.class
            ).setParameter("hommeId", hommeId).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

