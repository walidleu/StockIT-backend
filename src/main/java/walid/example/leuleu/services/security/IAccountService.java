package walid.example.leuleu.services.security;

import walid.example.leuleu.dto.BaseResponse;
import walid.example.leuleu.dto.ChangePasswordDto;
import walid.example.leuleu.models.security.AppRole;
import walid.example.leuleu.models.security.AppUser;

import java.util.List;

public interface IAccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username,String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> findAllUsers();
    boolean isExistByUsername(String username);
    BaseResponse changePassword(ChangePasswordDto changePasswordDto);
    BaseResponse newPassword(ChangePasswordDto changePasswordDto);

    BaseResponse deleteUserByUsername(String username);

}