

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
class CreateUseCaseTest {

    @Autowired
    QuestionRepository questionRepository;

    CreateUseCase createUseCase;


    @BeforeEach
    public void setup() {
        MapperUtils mapperUtils = new MapperUtils();
        questionRepository = mock(QuestionRepository.class);
        createUseCase = new CreateUseCase(mapperUtils, questionRepository);
    }

    @Test
    void createQuestionTest() {
        var question = new Question();
        question.setId("bbbb");
        question.setUserId("xxxx-xxxx");
        question.setType("tech");
        question.setCategory("software");
        question.setQuestion("¿Que es java?");
        when(questionRepository.save(anyObject())).thenReturn(Mono.just(question));

        QuestionDTO questionDTO = new QuestionDTO(
                question.getId(),
                question.getUserId(), question.getQuestion(), question.getType(), question.getCategory()
        );


        StepVerifier.create(createUseCase.apply(questionDTO))
                .expectNextMatches(questiondto-> {
                    assert questionDTO.getUserId().equals("xxxx-xxxx");
                    return true;
                })
                .verifyComplete();

    }

}
