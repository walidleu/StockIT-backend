package walid.example.leuleu.controlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;  // Import PasswordEncoder
import org.springframework.web.bind.annotation.*;
import walid.example.leuleu.dto.BaseResponse;
import walid.example.leuleu.dto.UserDto;
import walid.example.leuleu.models.security.AppUser;
import walid.example.leuleu.repositories.security.AppUserRepository;
import walid.example.leuleu.services.security.AccountService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    public UserController(AccountService accountService, PasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/addnewuser")
    public ResponseEntity<BaseResponse> addUserWithRoleAndPassword(
            @RequestBody UserDto user) {

        accountService.addNewUser(new AppUser(0, user.getUsername(), user.getPassword(), new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
        accountService.addRoleToUser(user.getUsername(),user.getRole());
        return ResponseEntity.ok(BaseResponse.success());
    }
    @GetMapping("/users")
    public List<AppUser> getAllusers()
    {
        return accountService.findAllUsers();
    }

    @GetMapping ("/user/{username}")
    public AppUser getUserByUsername(@PathVariable String username)
    {
        return accountService.loadUserByUsername(username);
    }
    @DeleteMapping("/deleteuser/{username}")
    public ResponseEntity<BaseResponse> deleteuser(@PathVariable String username)
    {
        accountService.deleteUserByUsername(username);
                return ResponseEntity.ok(BaseResponse.success());
    }
}
