package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class GetUseCaseTest {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    GetUseCase getUseCase;


    @BeforeEach
    public void setup() {
        answerRepository = mock(AnswerRepository.class);
        questionRepository = mock(QuestionRepository.class);
        MapperUtils mapperUtils = new MapperUtils();

        getUseCase = new GetUseCase(mapperUtils, questionRepository, answerRepository);
    }

    @Test
    void getAllAnswersByQuestionIdTest(){


        var answer = new Answer();
        answer.setUserId("xxxx-xxxx");
        answer.setQuestionId("123");
        answer.setAnswer("Es un lenguaje de programación");

        var question = new Question();
        question.setId("123");
        question.setUserId("xxxx-xxxx");
        question.setType("tech");
        question.setCategory("software");
        question.setQuestion("¿Que es java?");


        when(questionRepository.findById(anyString())).thenReturn(Mono.just(question));
        when(answerRepository.findAllByQuestionId(anyString())).thenReturn(Flux.just(answer));

        StepVerifier.create(getUseCase.apply("123"))
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getId().equals("123");
                    //assert questionDTO.getAnswers().get(0).getQuestionId().equals("123");
                    return true;

                })
                .verifyComplete();
    }
}