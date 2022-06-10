package com.shane.greatideas.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shane.greatideas.models.Idea;
import com.shane.greatideas.models.User;
import com.shane.greatideas.services.IdeaService;

@Controller
public class Ideas {
	@Autowired
	private IdeaService ideaService;
	
	@GetMapping("/ideas/new")
	public String newIdea(@ModelAttribute("idea") Idea idea, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		return "newIdea.jsp";
	}
	
	@PostMapping("/ideas/new")
	public String createIdea(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()){
			return "newIdea.jsp";
		} else {
			this.ideaService.createIdea(idea);
			return "redirect:/ideas";
		}	
	}
	
	@GetMapping("/ideas/{id}")
	public String idea(Model model, @PathVariable(value="id") Long id, HttpSession session) {
		Idea idea = ideaService.findIdea(id);
		User user = (User) session.getAttribute("user");
		model.addAttribute("idea", idea);
		model.addAttribute("user", user);
		return "idea.jsp";
	}
	
	@RequestMapping("/ideas/{id}/edit")
	public String editIdea(Model model, @PathVariable(value="id") Long id, HttpSession session) {
		Idea idea = ideaService.findIdea(id);
		User user = (User) session.getAttribute("user");
		model.addAttribute("idea", idea);
		model.addAttribute("user", user);
		return "edit.jsp";
	}
	
	@RequestMapping(value="/ideas/{id}/edit", method=RequestMethod.PUT)
	public String updateIdea(@Valid @ModelAttribute("editIdea") Idea idea, BindingResult result) {
		if(result.hasErrors()){
			return "edit.jsp";
		} else {
			this.ideaService.createIdea(idea);
			return "redirect:/ideas";
		}	
	}
	
	@RequestMapping(value="/ideas/{id}/delete", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Long id) {
		ideaService.deleteIdea(id);
		return "redirect:/ideas";
	}
}


