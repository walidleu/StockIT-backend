package walid.example.leuleu.models;
import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import walid.example.leuleu.enums.EBesoin;
import walid.example.leuleu.enums.EPret;
import walid.example.leuleu.enums.ERecuperation;
import walid.example.leuleu.models.security.AppUser;

@Data
@Entity @AllArgsConstructor
@NoArgsConstructor
public class Historique {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date DateSortie;
    @Temporal(TemporalType.DATE)
    private Date DateEntree;
    @Enumerated(EnumType.STRING)
    private EBesoin besoin;
    @Enumerated(EnumType.STRING)
    private EPret pret;
    @Enumerated(EnumType.STRING)
    private ERecuperation recuperation;
    @ManyToOne
    @JoinColumn(name="id_user")
    private AppUser user;
    @ManyToOne
    @JoinColumn(name="id_materiel")
    private Materiel materiel;
    @ManyToOne
    @JoinColumn(name="id_collaborateur")
    private Collaborateur collaborateur;

}
