package co.com.sofka.questions.usecases;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class DeleteUseCaseTest {

    QuestionRepository questionRepository;

    AnswerRepository answerRepository;

    DeleteUseCase deleteUseCase;

    @Test
    public void deleteAnswerandQuestionTest() {
        answerRepository = mock(AnswerRepository.class);
        questionRepository = mock(QuestionRepository.class);

        deleteUseCase = new DeleteUseCase(answerRepository, questionRepository);
        when(answerRepository.deleteById(anyString())).thenReturn(Mono.empty());
        when(questionRepository.deleteById(anyString())).thenReturn(Mono.empty());

        //StepVerifier.create(deleteUseCase.apply("xxxx-xxxx"))
        //      .verifyComplete();


    }

    @Test
    public void deleteErrorByIdNullTest() {
        answerRepository = mock(AnswerRepository.class);
        questionRepository = mock(QuestionRepository.class);

        deleteUseCase = new DeleteUseCase(answerRepository, questionRepository);
        when(answerRepository.deleteById(anyString())).thenReturn(Mono.empty());
        when(questionRepository.deleteById(anyString())).thenReturn(Mono.empty());

        StepVerifier.create(deleteUseCase.apply(""))
                .expectError(NullPointerException.class).verify();


    }
}