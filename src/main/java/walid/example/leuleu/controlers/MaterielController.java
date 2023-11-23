package walid.example.leuleu.controlers;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import walid.example.leuleu.enums.ECategorie;
import walid.example.leuleu.models.Materiel;
import walid.example.leuleu.repositories.MaterielRepository;

import java.util.*;

@RestController
@Transactional
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class MaterielController {

    private MaterielRepository materielRepository;
     @GetMapping(path="/materiel/{id}")
    public Materiel getMateriel(@PathVariable Long id)
    {
        return materielRepository.findById(id).orElse(null);
    }

    @GetMapping(path="/materiels")
    public List<Materiel> getAllMateriel()
    {
        return materielRepository.findAll();
    }
    @PostMapping(path="/addmateriel")
    public Materiel addMateriel(@RequestBody Materiel materiel)
    {
        return materielRepository.save(materiel);
    }
    @DeleteMapping (path = "/deletemateriel/{id}")
    public void deleteMateriel(@PathVariable Long id)
    {
        materielRepository.deleteById(id);
        System.out.println("Materiel supprimme !!"+id);
    }

  @GetMapping(path="/searchbymarque/{marque}")
    public List<Materiel> findAllByMarque(@PathVariable String marque){
         return materielRepository.findAllByMarqueContainsIgnoreCase(marque);
  }
    @GetMapping(path="/searchbyserialnumber/{numSerie}")
    public List<Materiel> findAllBynumSerie(@PathVariable String numSerie){
        return materielRepository.findAllBynumSerieContainsIgnoreCase(numSerie);
    }
    @GetMapping(path="/getmaterielid")
    public Materiel getMaterielId(@RequestParam String numserie)
    {
        return materielRepository.findMaterielIdByNumSerie(numserie);
    }
    @PutMapping(path = ("/updatestatus/{id}"))
    public Materiel updateMaterialStatus(@PathVariable Long id) {
            Materiel materiel = materielRepository.findById(id)
                    .orElseThrow();

            if(materiel.getStatus().equals("disponible"))
            {materiel.setStatus("nondisponible");}
            return materielRepository.save(materiel);
        }
    @PutMapping(path = ("/updatestatusreturn/{id}"))
    public Materiel updateMaterialReturnStatus(@PathVariable Long id) {
        Materiel materiel = materielRepository.findById(id)
                .orElseThrow();

        if(materiel.getStatus().equals("nondisponible"))
        {materiel.setStatus("disponible");}
        return materielRepository.save(materiel);
    }
    @PutMapping(path=("/updatecategorie/{id}"))
    public Materiel updateMaterialCategorie(@PathVariable Long id, @RequestBody String categorie) {
        Materiel materiel = materielRepository.findById(id)
                .orElseThrow();
        materiel.setCategorie(ECategorie.RECYCLE);
        return materielRepository.save(materiel);
    }

    @GetMapping(path=("/etatdustock"))
    public List<Object[]> etatDuStock()
    {
        return materielRepository.countMaterielByMarqueAndStatus();
    }
}
