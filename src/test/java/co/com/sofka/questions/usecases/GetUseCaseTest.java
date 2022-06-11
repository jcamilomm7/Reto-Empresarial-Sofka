package co.com.sofka.questions.usecases;


import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;


public class GetUseCaseTest {

    QuestionRepository questionRepository;

    AnswerRepository answerRepository;

    GetUseCase getUseCase;

    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        questionRepository = mock(QuestionRepository.class);
        answerRepository = mock(AnswerRepository.class);
        getUseCase = new GetUseCase(mapperUtils, questionRepository,answerRepository);
    }


    @Test
    void apply(){
        var question =  new Question();
        var answer = new Answer();
        answer.setQuestionId("aaaa");
        answer.setId("bbbb");
        question.setId("aaaa");
       question.setUserId("xxxx-xxxx");
        question.setType("tech");
        question.setCategory("software");
        question.setQuestion("¿Que es java?");
        when(questionRepository.findById(question.getId())).thenReturn(Mono.just(question));
        when(answerRepository.findAllByQuestionId(answer.getQuestionId())).thenReturn(Flux.just(answer));
        StepVerifier.create(getUseCase.apply(question.getId()))
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getUserId().equals("xxxx-xxxx");
                    assert questionDTO.getCategory().equals("software");
                    assert questionDTO.getQuestion().equals("¿Que es java?");
                    assert questionDTO.getType().equals("tech");
                    return true;
                })
                .verifyComplete();

        verify(questionRepository).findById(question.getId());
        verify(answerRepository).findAllByQuestionId(answer.getQuestionId());
    }


}