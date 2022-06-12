package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AddAnswerUseCaseTest {

    @Autowired
    AnswerRepository answerRepository;

    AddAnswerUseCase addAnswerUseCase;

    @InjectMocks
    GetUseCase getUseCase;

    @BeforeEach
    public void setup() {
        answerRepository = mock(AnswerRepository.class);
        getUseCase = mock(GetUseCase.class);
        MapperUtils mapperUtils = new MapperUtils();

        addAnswerUseCase = new AddAnswerUseCase(mapperUtils, getUseCase, answerRepository);
    }

    @Test
    void addAnswerTest() {
        var question = new Question();
        question.setUserId("xxxx-xxxx");
        question.setType("tech");
        question.setCategory("software");
        question.setQuestion("¿Que es java?");

        var questionDTOInit = new QuestionDTO();
        questionDTOInit.setUserId("xxxx-xxxx");
        questionDTOInit.setType("tech");
        questionDTOInit.setCategory("software");
        questionDTOInit.setQuestion("¿Que es java?");

        var answer = new Answer();
        answer.setUserId("xxxx-xxxx");
        answer.setQuestionId("123");
        answer.setAnswer("Es un lenguaje de programación");

        var answerDTO = new AnswerDTO();
        answerDTO.setUserId("xxxx-xxxx");
        answerDTO.setQuestionId("123");
        answerDTO.setAnswer("Es un lenguaje de programación");


        when(answerRepository.save(anyObject())).thenReturn(Mono.just(answer));
        when(getUseCase.apply(anyString())).thenReturn(Mono.just(questionDTOInit));

        StepVerifier.create(addAnswerUseCase.apply(answerDTO))
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getUserId().equals("xxxx-xxxx");
                    return true;
                })
                .verifyComplete();

        //verify(answerRepository).save(answer);
    }
}
