package co.com.sofka.questions.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class AuthService {

    @Autowired
    private FirebaseAuth firebaseAuth;

    @Cacheable("users")
    public UserRecord getUserFromUid(String uid) throws FirebaseAuthException {
        log.info("Invoked");
        return firebaseAuth.getUser(uid);
    }


}
