package com.siva.quizapp.service;

import com.siva.quizapp.dao.QuestionDao;
import com.siva.quizapp.dao.QuizDao;
import com.siva.quizapp.model.Question;
import com.siva.quizapp.model.QuestionWrapper;
import com.siva.quizapp.model.Quiz;
import com.siva.quizapp.model.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {


    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;



    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findAllByCategoryAndQuestionType(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz Created!!!", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
        Optional<Quiz> quiz = quizDao.findAllById(id);

        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();

        for (Question question : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            questionForUser.add(qw);

        }
        return new ResponseEntity<>(questionForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<QuizResponse> quizResponses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;

        for (QuizResponse quizResponse : quizResponses) {
           if(quizResponse.getResponse().equals(questions.get(i).getRightAnswer()))
               right++;
           i++;
        }
    return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
