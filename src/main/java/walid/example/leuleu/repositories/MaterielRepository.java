package walid.example.leuleu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import walid.example.leuleu.models.Materiel;

import java.util.List;

@Repository

public interface MaterielRepository extends JpaRepository<Materiel,Long> {
    @Query("SELECT m FROM Materiel m WHERE m.marque LIKE %:marque%")
    public List<Materiel> findAllByMarqueContainsIgnoreCase(String marque);
    @Query("SELECT m FROM Materiel m WHERE m.numSerie LIKE %:numeroSerie%")
    public List<Materiel> findAllBynumSerieContainsIgnoreCase(String numeroSerie);

    @Query("SELECT m FROM Materiel m WHERE m.numSerie = :numserie ")
    Materiel findMaterielIdByNumSerie( @Param("numserie") String numserie);

    @Query("SELECT m.marque AS marque, COUNT(m.id) AS nombre, m.status AS status FROM Materiel m GROUP BY m.marque, m.status")
    List<Object[]> countMaterielByMarqueAndStatus();

}
