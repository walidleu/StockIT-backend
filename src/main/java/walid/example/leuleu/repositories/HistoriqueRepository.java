package walid.example.leuleu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import walid.example.leuleu.models.Historique;

import java.util.Date;
import java.util.List;

@Repository
public interface HistoriqueRepository extends JpaRepository<Historique,Long> {
    @Query("SELECT h FROM Historique h WHERE h.materiel.id = :id_materiel AND h.DateEntree = null")
    public Historique FindHistoriqueByMaterielid(@Param("id_materiel")Long id_materiel);

    @Query("SELECT h FROM Historique h WHERE CONCAT(h.collaborateur.nom,' ', h.collaborateur.prenom) LIKE %:nom_prenom_collab%")
    public List<Historique> FindHistoriqueByCollaborateur(@Param("nom_prenom_collab") String nom_prenom_collab );

    @Query("SELECT h FROM Historique h WHERE h.materiel.numSerie LIKE %:numserie%")
    public List<Historique> FindHistoriqueByNumSerie(@Param("numserie")String numserie);

}
