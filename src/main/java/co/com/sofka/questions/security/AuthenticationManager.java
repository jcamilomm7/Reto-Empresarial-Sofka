package co.com.sofka.questions.security;

import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    private FirebaseAuthConfig firebaseAuthConfig;

    @Override
    @SuppressWarnings("unchecked")
    public Mono authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        String username;
        FirebaseAuth auth= null;
        try {
            auth = firebaseAuthConfig.firebaseAuth();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (auth != null ) {

            return Mono.just(auth);
        } else {
            return Mono.empty();
        }
    }
}