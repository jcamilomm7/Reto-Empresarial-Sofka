package co.com.sofka.questions.routers;

import co.com.sofka.questions.security.AuthService;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


public class LoginRouter {

    @Autowired
    private AuthService authService;

    @GetMapping(path = "/test")
    public String test(Principal principal) throws FirebaseAuthException {

        return "FUnccciona";
    }
}
