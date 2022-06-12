package co.com.sofka.questions.routers;

import co.com.sofka.questions.security.AuthService;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

//@RestController
//@RequestMapping("/")
public class LoginRouter {



    //@GetMapping(path = "/test")
    public Mono<String> test(Principal principal) throws FirebaseAuthException {

        return Mono.just("FUnccciona");
    }
}
