package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;






@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetAllUseCaseTest {

    /*@Mock
    private QuestionRepository questionRepository;*/

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testGetDatos() {

        webTestClient.get()
                .uri("/getAll")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Question.class)
                .consumeWith(response -> {
                    List<Question> questions = response.getResponseBody();
                    questions.forEach(p ->{
                        System.out.println(p.getQuestion());
                    });
                    //Con esta linea de codigo se podria comparar el tamaño de retorno, para ser una validacion con esta
                    //Assertions.assertThat(questions.size()==2).isTrue();

                });



    }

}