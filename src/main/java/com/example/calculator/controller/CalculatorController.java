package com.example.calculator.controller;

import com.example.calculator.entity.User;
import com.example.calculator.service.CalculatorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("loggedIn", user != null);
        model.addAttribute("username", user != null ? user.getUsername() : "");
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("num1") double num1,
            @RequestParam("num2") double num2,
            @RequestParam("operator") String operator,
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("user");
        
        if (("*".equals(operator) || "/".equals(operator)) && user == null) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "乘除运算需要先登录");
            model.addAttribute("loggedIn", false);
            model.addAttribute("username", "");
            model.addAttribute("result", "0");
            return "calculator";
        }

        try {
            String result = calculatorService.calculate(num1, num2, operator);
            model.addAttribute("num1", num1);
            model.addAttribute("num2", num2);
            model.addAttribute("operator", operator);
            model.addAttribute("result", result);
            model.addAttribute("error", false);
            model.addAttribute("errorMessage", "");
        } catch (IllegalArgumentException e) {
            model.addAttribute("num1", num1);
            model.addAttribute("num2", num2);
            model.addAttribute("operator", operator);
            model.addAttribute("result", "0");
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("loggedIn", user != null);
        model.addAttribute("username", user != null ? user.getUsername() : "");
        return "calculator";
    }
}
