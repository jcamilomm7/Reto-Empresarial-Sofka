package co.com.sofka.questions.routers;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecases.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@CrossOrigin(origins = "http://localhost:4200")
@Configuration
public class QuestionRouter {

    @Bean
    @RouterOperation(path = "/getAll"
            , produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET, beanClass = ListUseCase.class, beanMethod = "get",
            operation = @Operation(operationId = "get",
                    responses = { @ApiResponse(responseCode = "200",
                            description = "get all",
                            content = @Content(schema = @Schema(implementation = QuestionDTO.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid question ID supplied"),
                            @ApiResponse(responseCode = "404", description = "Question not found")}))
    public RouterFunction<ServerResponse> getAll(ListUseCase listUseCase) {
        return route(GET("/getAll"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listUseCase.get(), QuestionDTO.class))
        );
    }
    @Bean
    public RouterFunction<ServerResponse> all(ListUseCase listUseCase) {
        return route(GET("/all"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue("ddd"))
        );
    }
    @Bean
    public RouterFunction<ServerResponse> all(ListUseCase listUseCase) {
        return route(GET("/all"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue("ssss"))
        );
    }


    @Bean

    @RouterOperation(path = "/getOwnerAll/{Id}"
            , produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET, beanClass = OwnerListUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "apply",
                    responses = { @ApiResponse(responseCode = "200", description = "get Owner All",
                            content = @Content(schema = @Schema(implementation = QuestionDTO.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid question ID supplied"),
                            @ApiResponse(responseCode = "404", description = "Question not found")})
    )
    public RouterFunction<ServerResponse> getOwnerAll(OwnerListUseCase ownerListUseCase) {
        return route(
                GET("/getOwnerAll/{Id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                ownerListUseCase.apply(request.pathVariable("Id")),
                                QuestionDTO.class
                         ))
        );
    }

    @Bean

    @RouterOperation(path = "/create"
            , produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, beanClass = CreateUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "save",
                    responses = { @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = QuestionDTO.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee ID supplied"),
                            @ApiResponse(responseCode = "404", description = "Employee not found")}
                    , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = QuestionDTO.class))))
    )
    public RouterFunction<ServerResponse> create(CreateUseCase createUseCase) {
        Function<QuestionDTO, Mono<ServerResponse>> executor = questionDTO ->  createUseCase.apply(questionDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                POST("/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(QuestionDTO.class).flatMap(executor)
        );
    }

    @Bean
    @RouterOperation(path = "/get/{Id}"
            , produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET, beanClass = GetUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "apply",
                    responses = { @ApiResponse(responseCode = "200", description = "get",
                            content = @Content(schema = @Schema(implementation = QuestionDTO.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid question ID supplied"),
                            @ApiResponse(responseCode = "404", description = "Question not found")}
                    )
    )
    public RouterFunction<ServerResponse> get(GetUseCase getUseCase) {
        return route(
                GET("/get/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getUseCase.apply(
                                request.pathVariable("id")),
                                QuestionDTO.class
                        ))
        );
    }

    @Bean

    @RouterOperation(path = "/add"
            , produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, beanClass = AddAnswerUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "apply",
                    responses = { @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = AnswerDTO.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid operation"),
                            @ApiResponse(responseCode = "404", description = "question not added")}
                    , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = AnswerDTO.class))))
    )
    public RouterFunction<ServerResponse> addAnswer(AddAnswerUseCase addAnswerUseCase) {
        return route(POST("/add").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(AnswerDTO.class)
                        .flatMap(addAnswerDTO -> addAnswerUseCase.apply(addAnswerDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }

    @Bean
    @RouterOperation(path = "/delete/{Id}"
            , produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.DELETE, beanClass = DeleteUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "apply",
                    responses = { @ApiResponse(responseCode = "200", description = "deleted",
                            content = @Content(schema = @Schema(implementation = QuestionDTO.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid question ID supplied"),
                            @ApiResponse(responseCode = "404", description = "question not found")})
    )
    public RouterFunction<ServerResponse> delete(DeleteUseCase deleteUseCase) {
        return route(
                DELETE("/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }
}
