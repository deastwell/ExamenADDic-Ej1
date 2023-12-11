package cesur.examen.domain.car;

import cesur.examen.common.DAO;
import cesur.examen.common.HibernateUtil;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno: Daniel Eastwell
 * Fecha:
 */

@Log
public class CarDAO implements DAO<Car> {
    @Override
    public Car save(Car car) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = null;
        try {
            ts = session.beginTransaction();
            session.save(car);
            ts.commit();
        } catch (Exception e) {
            if (ts != null) ts.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return car;

    }

    @Override
    public Car update(Car car) {
        return null;
    }

    @Override
    public boolean remove(Car car) {
        return false;
    }

    @Override
    public Car get(Long id) {
        return null;
    }

    @Override
    public List<Car> getAll() {
        return null;
    }

    public List<Car> getAllByManufacturer(String manufacturer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Car> cars  = new ArrayList<>();
        try {
            String query = "from Car where manufacturer = :manufacturer";
            cars = session.createQuery(query, Car.class)
                    .setParameter("manufacturer", manufacturer)
                    .list();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cars;
    }



}
