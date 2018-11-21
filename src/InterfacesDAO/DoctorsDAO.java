package InterfacesDAO;

import Entity.Doctors;

import java.util.List;

public interface DoctorsDAO {
    //public String getCreateTableString();

    public void add(Doctors Doctor);

    public Doctors get(int id);

    public void remove(int id);

    public void update(Doctors doctor);

    public List<Doctors> getAll();
}
