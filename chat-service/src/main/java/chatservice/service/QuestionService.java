package chatservice.service;

import chatservice.domain.Product;
import chatservice.domain.Question;
import chatservice.domain.User;
import chatservice.dto.AnswerDtoRequest;
import chatservice.dto.QuestionDtoRequest;
import chatservice.dto.QuestionDtoResponse;
import chatservice.exception.QuestionException;
import chatservice.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import chatservice.repository.ProductRepository;
import chatservice.repository.QuestionRepository;
import chatservice.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public QuestionDtoResponse askQuestion(Long customerId, Long productId, QuestionDtoRequest questionDtoRequest) {
        Product product = getProductFromDbDById(productId);
        User customer = getUserFromDbById(customerId);

        Question question = Question.builder()
                .questionText(questionDtoRequest.getQuestionText())
                .sendDate(LocalDateTime.now())
                .product(product)
                .customer(customer)
                .build();
        question = questionRepository.save(question);

        return getQuestionDtoResponse(question);
    }

    public QuestionDtoResponse getAnswerByQuestion(Long customerId, Long questionId) {
        Question question = getQuestionFromDbById(questionId);

        if (question.getCustomer().getId() != customerId) {
            throw new UserException("Данный вопрос не принадлежит пользователю");
        }

        return getQuestionDtoResponse(question);
    }

    public List<QuestionDtoResponse> getQuestionsByProduct(Long sellerId, Long productId) {
        Product product = getProductFromDbDById(productId);
        if (product.getOwner().getId() != sellerId) {
            throw new IllegalArgumentException();
        }
        List<Question> questionList = product.getQuestions();

        return getListQuestionDtoResponse(questionList);
    }

    public QuestionDtoResponse getQuestionById(Long sellerId, Long questionId) {
        Question question = getQuestionFromDbById(questionId);

        return getQuestionDtoResponse(question);
    }

    public QuestionDtoResponse answer(Long sellerId, Long questionId, AnswerDtoRequest answerDtoRequest) {
        Question question = getQuestionFromDbById(questionId);
        question.setAnswer(answerDtoRequest.getAnswer());

        question = questionRepository.save(question);

        return getQuestionDtoResponse(question);
    }

    private List<QuestionDtoResponse> getListQuestionDtoResponse(List<Question> questions) {
        List<QuestionDtoResponse> result = new ArrayList<>();
        for (Question question : questions) {
            result.add(getQuestionDtoResponse(question));
        }
        return result;
    }

    private QuestionDtoResponse getQuestionDtoResponse(Question question) {
        return QuestionDtoResponse.builder()
                .id(question.getId())
                .text(question.getQuestionText())
                .sendDate(question.getSendDate())
                .build();
    }

    private Product getProductFromDbDById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    private User getUserFromDbById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException("Пользователя с id = " + userId + "нет в базе данных"));
    }

    private Question getQuestionFromDbById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionException("Вопроса с id = " + questionId + " нет в базе данных"));
    }

}
