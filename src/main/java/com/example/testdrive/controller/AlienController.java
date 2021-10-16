package com.example.testdrive.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.testdrive.model.Alien;
import com.example.testdrive.repo.AlienRepo;

@Controller
public class AlienController {
	@Autowired
	AlienRepo repo;

	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}

	@RequestMapping("/addAlien")
	public String addAlien(Alien alien) {
		repo.save(alien);
		return "home.jsp";
	}

	@RequestMapping("/getAlien")
	public ModelAndView showAlien(@RequestParam int aid) {
		ModelAndView mv = new ModelAndView("showAlien.jsp");
		Alien alien = repo.findById(aid).orElse(new Alien());
		mv.addObject(alien);
		System.out.println("Aliens with Java Lang" + repo.findByLanguage("Java"));
		System.out.println("Aliens with Grt Than 104" + repo.findByAidGreaterThan(104));
		System.out.println("Aliens with Java Lang sorted by Name" + repo.findByLanguageSortedWithName("Java"));
		return mv;
	}

	@RequestMapping("/aliens")
	@ResponseBody
	public String getAliens() {
		return repo.findAll().toString();
	}

	@RequestMapping("/alien/{aid}")
	@ResponseBody
	public String getAliens(@PathVariable(value = "aid") int aid) {
		return repo.findById(aid).toString();
	}

}
