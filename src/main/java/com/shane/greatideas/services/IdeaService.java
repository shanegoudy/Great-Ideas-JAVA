package com.shane.greatideas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shane.greatideas.models.Idea;
import com.shane.greatideas.repositories.IdeaRepository;

@Service
public class IdeaService {
	@Autowired
	private final IdeaRepository ideaRepository;
	
	public IdeaService(IdeaRepository ideaRepository) {
		this.ideaRepository = ideaRepository;
	}
	
	public List<Idea> allIdeas(){
		return ideaRepository.findAll();
	}
	
	public Idea createIdea(Idea idea) {
		return ideaRepository.save(idea);
	}
	
	public Idea findIdea(Long id) {
		Optional<Idea> optionalIdea = ideaRepository.findById(id);
		if(optionalIdea.isPresent()) {
			return optionalIdea.get();
		} else {
			return null;
		}	
	}
	
	public void deleteIdea(Long id) {
		ideaRepository.deleteById(id);
	}
	
	public List<Idea> findIdeaByUser(Long id){
		List<Idea> listIdea = ideaRepository.findByuser_id(id);
		return listIdea;
	}
}