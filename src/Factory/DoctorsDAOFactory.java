package Factory;



import Dao.DoctorsDAOImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class DoctorsDAOFactory {
    @PersistenceContext(unitName="Doctors")
    private EntityManager em;

    @Produces
    public DoctorsDAOImpl createDoctorsDAO() {
        return new DoctorsDAOImpl(em);
    }
}
