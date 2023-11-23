package walid.example.leuleu.controlers;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import walid.example.leuleu.models.Collaborateur;
import walid.example.leuleu.models.Historique;
import walid.example.leuleu.repositories.CollaborateurRepository;

import java.util.List;


@RestController
@Transactional
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class CollaborateurController {

    private CollaborateurRepository collaborateurRepository;

    @PostMapping(path="/addcollaborateur")
    public Collaborateur saveCollaborateur(@RequestBody Collaborateur collaborateur)
    {
        return collaborateurRepository.save(collaborateur);
    }
    @GetMapping(path="/collaborateur/{id}")
    public Collaborateur getCollaborateur(@PathVariable Long id)
    {
        return collaborateurRepository.findById(id).orElseThrow();
    }

    @GetMapping(path="/collaborateurs")
    //@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public List<Collaborateur> getAllCollaborateur()
    {
        return collaborateurRepository.findAll();
    }

    @GetMapping("/getCollaboratorId")
    public Collaborateur getCollaboratorId(@RequestParam String nom, @RequestParam String prenom) {
        return collaborateurRepository.findCollaboratorIdByNameAndSurname(nom, prenom);
    }
}
