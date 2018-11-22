package InterfacesDAO;

import java.util.List;

public class SpecialtyDAO {
    public void add(Specialty Doctor);

    public Specialty get(int id);

    public void remove(int id);

    public void update(Specialty doctor);

    public List<Specialty> getAll();
}
