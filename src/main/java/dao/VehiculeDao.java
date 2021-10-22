package dao;

import model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculeDao extends JpaRepository<Vehicule, Integer> {
    Vehicule findById(int id);
}
