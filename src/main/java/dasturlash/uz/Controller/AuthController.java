package dasturlash.uz.Controller;

import dasturlash.uz.DTO.ProfileLoginDTO;
import dasturlash.uz.DTO.auth.RegistrationDTO;
import dasturlash.uz.DTO.profile.ProfileUpdateDTO;
import dasturlash.uz.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@Valid @RequestBody RegistrationDTO dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @GetMapping("/registration/email/verification/{jwt}")
    public ResponseEntity<String> registration(@PathVariable("jwt") String jwt) {
        return ResponseEntity.ok(authService.regEmailVerification(jwt));
    }
    @PostMapping("/login")
    public ResponseEntity<ProfileUpdateDTO> login(@RequestBody ProfileLoginDTO dto){
        return ResponseEntity.ok(authService.login(dto));
    }
}