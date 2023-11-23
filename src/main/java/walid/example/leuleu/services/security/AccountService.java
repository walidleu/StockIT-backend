package walid.example.leuleu.services.security;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import walid.example.leuleu.dto.BaseResponse;
import walid.example.leuleu.dto.ChangePasswordDto;
import walid.example.leuleu.exceptions.RecordNotFoundException;
import walid.example.leuleu.models.security.AppRole;
import walid.example.leuleu.models.security.AppUser;
import walid.example.leuleu.repositories.security.AppRoleRepository;
import walid.example.leuleu.repositories.security.AppUserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class AccountService implements  IAccountService {
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final PasswordEncoder passwordEncoder;
    public AccountService(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser addNewUser(AppUser appUser) {
        String pwd = appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(pwd));
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = loadUserByUsername(username);
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        appUser.getRoles().add(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        Optional<AppUser> appUserOptional = appUserRepository.findByUsername(username);
        if (appUserOptional.isPresent()){
            return appUserOptional.get();
        }
        log.warn("Le User avec le username : " + username + " n'existe pas");
        throw new RecordNotFoundException("Le User avec le username : " + username + " n'existe pas");
    }

    @Override
    public List<AppUser> findAllUsers() {
        return appUserRepository.findAll();
    }

    public boolean isExistByUsername(String username){
        return  appUserRepository.existsAppUserByUsername(username);
    }

    @Override
    public BaseResponse changePassword(ChangePasswordDto changePasswordDto){
        AppUser appUser = this.loadUserByUsername(changePasswordDto.getUsername());
        boolean passwordsMatch = passwordEncoder.matches(changePasswordDto.getOldPassword(), appUser.getPassword());
        if (Boolean.FALSE.equals(passwordsMatch)){
            return new BaseResponse(false,"L'ancien mot de passe que vous avez saisie n'est pas correcte !");
        }

        appUser.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        appUserRepository.save(appUser);
        return BaseResponse.success();

    }

    @Override
    public BaseResponse newPassword(ChangePasswordDto changePasswordDto) {
        AppUser appUser = this.loadUserByUsername(changePasswordDto.getUsername());
        appUser.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        appUserRepository.save(appUser);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse deleteUserByUsername(String username) {
        AppUser appUser = this.loadUserByUsername(username);
        appUserRepository.delete(appUser);
        return BaseResponse.success();
    }

}
