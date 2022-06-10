package com.shane.greatideas.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shane.greatideas.models.Idea;
import com.shane.greatideas.models.User;
import com.shane.greatideas.services.IdeaService;
import com.shane.greatideas.services.UserService;
import com.shane.greatideas.validator.UserValidator;

@Controller
public class Users {
	
	@Autowired
	private final UserService userService;

	private final UserValidator userValidator;
	
	@Autowired
	private	IdeaService ideaService;

	public Users(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}

	@RequestMapping("/")
	public String registerForm(@ModelAttribute("user") User user, Model model, HttpSession session) {
		if(null != session.getAttribute("loginError")) {
			model.addAttribute("error", session.getAttribute("loginError"));
		}
		return "registrationPage.jsp";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "registrationPage.jsp";
		}
		User u = userService.registerUser(user);
		session.setAttribute("user", user);
		return "redirect:/ideas";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model,
			HttpSession session) {
		boolean isAuthenticated = userService.authenticateUser(email, password);
		if (isAuthenticated) {
			User user = userService.findByEmail(email);
			session.setAttribute("user", user);
			return "redirect:/ideas";
		} else {
			session.setAttribute("loginError", "Invalid Credentials. Please try again.");
			return "redirect:/";
		}
	}

	@RequestMapping("/ideas")
	public String home(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		List<Idea> ideas = ideaService.allIdeas();
		model.addAttribute("user", user);
		model.addAttribute("ideas", ideas);
		return "homePage.jsp";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}

