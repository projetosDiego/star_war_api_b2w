package com.b2w.starwars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.b2w.starwars.model.Planeta;
import com.b2w.starwars.service.PlanetaService;

@Controller
@RequestMapping("/controller")
public class StarWarsApiController {
	
	@Autowired
	public PlanetaService planetaService;
	
	@RequestMapping(value = "/planetas", method = RequestMethod.GET)
	public ResponseEntity<List<Planeta>> listarPlanetas(){
		return new ResponseEntity<List<Planeta>>(planetaService.listarPlanetas(), HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/planetas", method = RequestMethod.POST)
	public ResponseEntity<Planeta> salvarPlaneta(@RequestBody Planeta planeta) {
		return new ResponseEntity<Planeta>(planetaService.inserirPlaneta(planeta), HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/planetas/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<Planeta> buscarPorNome(@PathVariable String nome) {
		return new ResponseEntity<Planeta>(planetaService.buscarPorNome(nome), HttpStatus.FOUND);
    }
	
	@RequestMapping(value = "/planetas/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<Planeta> buscarPorId(@PathVariable String id) {
		return new ResponseEntity<Planeta>(planetaService.buscarPorId(id), HttpStatus.FOUND);
    }
	
	@RequestMapping(value = "/planetas/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Planeta> deletarPlaneta(@PathVariable String id) {
		planetaService.deletarPlaneta(id);
		return ResponseEntity.noContent().build();
    }
}
