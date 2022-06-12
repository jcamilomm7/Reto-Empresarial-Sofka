package co.com.sofka.questions.usecases;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UpdateUseCaseTest {

    @Autowired
    QuestionRepository questionRepository;

    UpdateUseCase updateUseCase;

    @BeforeEach
    public void setup() {
        questionRepository = mock(QuestionRepository.class);
        MapperUtils mapperUtils = new MapperUtils();

        updateUseCase = new UpdateUseCase(mapperUtils, questionRepository);
    }

    @Test
    void UpdateQuestionTest() {
        var questionDTOInit = new QuestionDTO();
        questionDTOInit.setId("HFC3456");
        questionDTOInit.setUserId("xxxx-xxxx");
        questionDTOInit.setType("tech");
        questionDTOInit.setCategory("software");
        questionDTOInit.setQuestion("¿Que es java?");

        var question = new Question();
        question.setId("HFC3456");
        question.setUserId("xxxx-xxxx");
        question.setType("tech");
        question.setCategory("software");
        question.setQuestion("¿Que es java?");

        when(questionRepository.save(anyObject())).thenReturn(Mono.just(question));

        StepVerifier.create(updateUseCase.apply(questionDTOInit))
                .expectNextMatches(s -> {
                    assert s.equals("HFC3456");
                    return true;
                })
                .verifyComplete();

    }
}