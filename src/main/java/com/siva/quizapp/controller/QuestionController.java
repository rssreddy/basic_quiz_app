package com.siva.quizapp.controller;

import com.siva.quizapp.model.Question;
import com.siva.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allquestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{questionCategory}")
    public ResponseEntity<List<Question>> getQuestionsByQuestionCategory(@PathVariable String questionCategory) {
        return questionService.getAllQuestionsByCategory(questionCategory);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }


}
