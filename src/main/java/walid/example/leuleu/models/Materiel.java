package walid.example.leuleu.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import walid.example.leuleu.enums.EBesoin;
import walid.example.leuleu.enums.ECategorie;
import walid.example.leuleu.enums.ETypeMateriel;
import walid.example.leuleu.models.security.AppUser;

import java.util.Date;
import java.util.List;


@Entity
@AllArgsConstructor @Data @NoArgsConstructor

public class Materiel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modele;
    private String marque;
    @Column(unique=true)
    private String numSerie;
    @Temporal(TemporalType.DATE)
    private Date dateAffectation;
    @Temporal(TemporalType.DATE)
    private Date dateEntreeInitiale;
    @Enumerated(EnumType.STRING)
    private ECategorie categorie;
    @Enumerated(EnumType.STRING)
    private ETypeMateriel typeMateriel;
    private String remarque;
    private String status;
    @ManyToOne
    @JoinColumn(name="id_user")
    private AppUser user;
    @JsonIgnore
    @OneToMany(mappedBy = "materiel")
    private List<Historique> historique;

}
