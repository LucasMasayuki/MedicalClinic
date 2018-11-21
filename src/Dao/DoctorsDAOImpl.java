package Dao;

import Entity.Doctors;
import InterfacesDAO.DoctorsDAO;

import javax.persistence.EntityManager;
import java.util.List;

public class DoctorsDAOImpl implements DoctorsDAO {
    private EntityManager em;

    public DoctorsDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void add(Doctors doctor) {
        em.persist(doctor);
    }

    @Override
    public Doctors get(int id) {
        return getAll().get(id);
    }
//
//    public String getCreateTableString() {
//        return this.createDoctorsTable;
//    }

    @Override
    public void remove(int id) {
        em.remove(get(id));
    }

    @Override
    public void update(Doctors doctor) {
        em.merge(doctor);
    }

    @Override
    public List<Doctors> getAll() {
        return em.createQuery("SELECT doctor FROM Doctors doctor", Doctors.class).getResultList();
    }
}
