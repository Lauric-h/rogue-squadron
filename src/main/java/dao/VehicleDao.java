package dao;

import model.Type;
import model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleDao extends JpaRepository<Vehicle, Integer> {
    Vehicle findById(int id);

    List<Vehicle> findByType(Type type);

}
