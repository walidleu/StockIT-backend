package walid.example.leuleu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import walid.example.leuleu.models.Collaborateur;

@Repository
public interface CollaborateurRepository extends JpaRepository<Collaborateur,Long > {
    @Query("SELECT c  FROM Collaborateur c WHERE c.nom = :nom AND c.prenom = :prenom")
    Collaborateur findCollaboratorIdByNameAndSurname(@Param("nom") String nom, @Param("prenom") String prenom);
}
