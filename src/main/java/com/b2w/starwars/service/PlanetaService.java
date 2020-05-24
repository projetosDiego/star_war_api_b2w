package com.b2w.starwars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.b2w.starwars.exception.ObjetoJaCadastrado;
import com.b2w.starwars.exception.ObjetoNaoEncontradoException;
import com.b2w.starwars.model.Planeta;
import com.b2w.starwars.model.PlanetaResult;
import com.b2w.starwars.repository.IPlanetaRepository;
import com.b2w.starwars.util.Constantes;

@Service
public class PlanetaService {
	
	@Autowired
	public IPlanetaRepository iPlanetaRepository;
	
	@Autowired
    private RestTemplate restTemplate;
	
	public List<Planeta> listarPlanetas(){
		List<Planeta> planetas = iPlanetaRepository.findAll(); 
		if(!planetas.isEmpty()) {
			return planetas;
		}else {
			throw new ObjetoNaoEncontradoException(Constantes.NO_PLANETS);
		}
	}
	
	public Planeta inserirPlaneta(Planeta planeta) {
		List<Planeta> planetaBd = iPlanetaRepository.findByNomePlanetaContaining(planeta.getNomePlaneta());
		if(planetaBd.isEmpty()) {
			PlanetaResult resultado = restTemplate.getForObject(Constantes.URL_PESQUISA + planeta.getNomePlaneta(), PlanetaResult.class);
			if(resultado != null && !resultado.getResults().isEmpty()) {
				planeta.setAparicoesEmFilmes(resultado.getResults().get(0).getContadorFilmes());
			}else {
				planeta.setAparicoesEmFilmes(Integer.valueOf(0));
			}
			return iPlanetaRepository.insert(planeta);
		}else {
			throw new ObjetoJaCadastrado(Constantes.ALREADY_USED_OLANET);
		}
	}

	public Planeta buscarPorNome(String nome) {
		List<Planeta> planeta = iPlanetaRepository.findByNomePlanetaContaining(nome);
		if(!planeta.isEmpty()) {
			return planeta.get(0);
		}else {
			throw new ObjetoNaoEncontradoException(Constantes.NOT_FOUND_NAME);
		}
	}

	public Planeta buscarPorId(String id) {
		Planeta planeta = iPlanetaRepository.findByPlanetaId(id);
		if(planeta != null) {
			return planeta;
		}else {
			throw new ObjetoNaoEncontradoException(Constantes.NOT_FOUND_ID);
		}
	}

	public void deletarPlaneta(String id) {
		Planeta planeta = iPlanetaRepository.findByPlanetaId(id);
		if(planeta != null) {
			iPlanetaRepository.delete(buscarPorId(id));
		}else {
			throw new ObjetoNaoEncontradoException(Constantes.NOT_FOUND_PLANET);
		}
	}
}
