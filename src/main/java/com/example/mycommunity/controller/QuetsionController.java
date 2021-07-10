package com.example.mycommunity.controller;

import com.example.mycommunity.dto.QuestionDTO;
import com.example.mycommunity.mapper.QuestionExrMapper;
import com.example.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Create by czl on 2021/7/8 18:53
 */
@Controller
public class QuetsionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model){
        QuestionDTO questionDTO=questionService.getById(id);
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
