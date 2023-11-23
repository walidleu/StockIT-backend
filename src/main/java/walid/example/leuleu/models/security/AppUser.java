package walid.example.leuleu.models.security;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.*;
import walid.example.leuleu.models.Historique;
import walid.example.leuleu.models.Materiel;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "APPLICATION_USER")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;
    @Column(unique=true,nullable = false,length = 25)
    String username;
    @Column(nullable = false)
    @JsonIgnore
    String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
    List<AppRole> roles = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Historique> historique;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Materiel> materiel;

}
