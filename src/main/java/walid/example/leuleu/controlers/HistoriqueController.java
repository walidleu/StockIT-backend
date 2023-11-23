package walid.example.leuleu.controlers;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import walid.example.leuleu.enums.ERecuperation;
import walid.example.leuleu.models.Historique;
import walid.example.leuleu.repositories.HistoriqueRepository;

@RestController
@Transactional
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")

public class HistoriqueController  {
    private HistoriqueRepository historiqueRepository;


    @GetMapping(path="/historiques")
    public List<Historique> getAllHIstorique(){return historiqueRepository.findAll();}

    @PostMapping(path="/addhistorique")
    public Historique saveHistorique(@RequestBody Historique historique)
    {
            return historiqueRepository.save(historique);
    }
    @DeleteMapping (path="/deletehistorique/{id}")
   public void deleteHistorique(@PathVariable Long id) {
        historiqueRepository.deleteById(id);
        System.out.println("Historique supprimé !!");
    }
    @GetMapping(path="/historique/{id}")
    public Historique getHistrorique(@PathVariable Long id)
    {
        return historiqueRepository.findById(id).orElseThrow();
    }
    @GetMapping(path="/historiquereturn")
    public Historique historiquereturn(@RequestParam Long id_materiel)
    {
        return historiqueRepository.FindHistoriqueByMaterielid(id_materiel);
    }
    @PutMapping(path="/updatedateentree/{id}")
    public Historique updatedateentree(@PathVariable Long id, @RequestBody Historique newHistorique )
    {
        return historiqueRepository.findById(id)
                .map(historique -> {
                        historique.setDateEntree(newHistorique.getDateEntree());
                        historique.setRecuperation(newHistorique.getRecuperation());
                        return historiqueRepository.save(historique);
                }).orElse(null);
    }

    @GetMapping(path = "/historiquesmateriel")
    public List<Historique> findhistoriquesmateriel(@RequestParam String numserie)
    {
        return historiqueRepository.FindHistoriqueByNumSerie(numserie);
    }

    @GetMapping(path = "/historiquescollaborateur")
    public List<Historique> findhistoriquescollaborateur(@RequestParam String nom_prenom_collab)
    {
        return historiqueRepository.FindHistoriqueByCollaborateur(nom_prenom_collab);
    }

}
