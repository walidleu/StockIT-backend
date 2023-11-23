package walid.example.leuleu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import walid.example.leuleu.models.security.AppRole;
import walid.example.leuleu.models.security.AppUser;
import walid.example.leuleu.security.RsaKeysConfig;
import walid.example.leuleu.services.security.AccountService;

import java.util.ArrayList;

@EnableConfigurationProperties(RsaKeysConfig.class)
@SpringBootApplication
public class LeuleuApplication {

	public static void main(String[] args) {


		SpringApplication.run(LeuleuApplication.class, args);


	}

	/*@Bean
	CommandLineRunner init(MaterielRepository materielRepository, ResponsableRepository responsableRepository){
		return args -> {
			MaterielController materielController=new MaterielController();
			Responsable responsable = responsableRepository.findById(1L).orElse(null);
			Materiel m=new Materiel();
			m.setBesoin(EBesoin.VOLE);
			m.setDateAffectation(new Date());
			m.setCategorie(ECategorie.NOUVEAU);
			m.setMarque("mac");
			m.setNumSerie("MPX4655");
			m.setModele("air");
			m.setRemarque("Evjklnj");
			m.setTypeMateriel(ETypeMateriel.LAPTOP);
			m.setResponsable(responsable);
			materielController.addMateriel(m);

		};
	}
	@Bean
	CommandLineRunner init(MaterielRepository materielRepository, ResponsableRepository responsableRepository
	, HistoriqueRepository historiqueRepository, CollaborateurRepository collaborateurRepository)
	{
		return args -> {
			MaterielController mc=new MaterielController();
			ReponsableController rc=new ReponsableController();
		};
	}*/

	@Bean
	public PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}

	@Bean
	public WebMvcConfigurer configure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry reg) {
				reg.addMapping("/**").allowedOrigins("*");
			}
		};

	}

	@Bean
	CommandLineRunner start(AccountService accountService){
		return args -> {
			//accountService.addNewRole(new AppRole(0,"ADMIN"));
//			accountService.addNewUser(new AppUser(0,"w123","w123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
//  	 		accountService.addRoleToUser("walid","ADMIN");
		};
	}

}
