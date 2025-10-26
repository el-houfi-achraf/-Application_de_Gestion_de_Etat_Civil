package ma.projet.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.projet.beans.Personne;
import ma.projet.dao.IDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonneService implements IDao<Personne> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean create(Personne personne) {
        try {
            entityManager.persist(personne);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Personne personne) {
        try {
            entityManager.merge(personne);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Personne personne) {
        try {
            entityManager.remove(entityManager.contains(personne) ? personne : entityManager.merge(personne));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Personne findById(int id) {
        try {
            return entityManager.find(Personne.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Personne> findAll() {
        try {
            return entityManager.createQuery("FROM Personne", Personne.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Personne findFemmePlusAgee() {
        try {
            List<Personne> result = entityManager.createQuery(
                "FROM Personne p WHERE TYPE(p) = Femme ORDER BY p.dateNaissance ASC",
                Personne.class
            ).setMaxResults(1).getResultList();
            return result.isEmpty() ? null : result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

