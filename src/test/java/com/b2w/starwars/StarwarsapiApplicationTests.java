package com.b2w.starwars;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.b2w.starwars.exception.ObjetoJaCadastrado;
import com.b2w.starwars.exception.ObjetoNaoEncontradoException;
import com.b2w.starwars.model.Planeta;
import com.b2w.starwars.model.PlanetaResult;
import com.b2w.starwars.model.PlanetaResultApi;
import com.b2w.starwars.repository.IPlanetaRepository;
import com.b2w.starwars.service.PlanetaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StarwarsapiApplicationTests {
	
	@Autowired
	private PlanetaService service;
	
	@MockBean
	private IPlanetaRepository repository;
	
	@MockBean
	private RestTemplate restTemplate;
	
	@SuppressWarnings("deprecation")
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	private Planeta criarInstanciaPlaneta() {
		Planeta planeta = new Planeta();
		planeta.setPlanetaId("1");
		planeta.setNomePlaneta("Tatooine");
		planeta.setClimaPlaneta("Quente e árido");
		planeta.setTerrenoPlaneta("Montanhas, Desertos, Desfiladeiros");
		planeta.setAparicoesEmFilmes(Integer.valueOf(5));
		return planeta;
	}
	
	private List<Planeta> CriarListaPlanetas() {
		List<Planeta> planetas = new ArrayList<Planeta>();
		planetas.add(criarInstanciaPlaneta());
		planetas.add(criarInstanciaPlaneta());
		return planetas;
	}
	
	private PlanetaResult criarInstanciaPlanetaResult() {
		PlanetaResult          planetaResult    = new PlanetaResult();
		List<PlanetaResultApi> results          = new ArrayList<PlanetaResultApi>();
		PlanetaResultApi       planetaResultApi = new PlanetaResultApi();
		String[]               filme            = {"Episódio 3", "episódio 4"};
		planetaResultApi.setName("Tatooine");
		planetaResultApi.setFilms(filme);
		results.add(planetaResultApi);
		planetaResult.setResults(results);
		return planetaResult;
	}
	
	@Test
	public void inserirPlanetaTest() {
		Planeta planeta = criarInstanciaPlaneta();
		Mockito.when(repository.findByNomePlanetaContaining("Tatooine")).thenReturn(new ArrayList<Planeta>());
		
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(PlanetaResult.class), Mockito.anyMap())).thenReturn(new PlanetaResult());
		service.inserirPlaneta(planeta);
		
		PlanetaResult planetaResult = criarInstanciaPlanetaResult();
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(PlanetaResult.class), Mockito.anyMap())).thenReturn(planetaResult);
		planeta.setAparicoesEmFilmes(planetaResult.getResults().get(0).getContadorFilmes());
		service.inserirPlaneta(planeta);
		
		Mockito.when(repository.findByNomePlanetaContaining("Tatooine")).thenReturn(CriarListaPlanetas());
		exception.expect(ObjetoJaCadastrado.class);
		service.inserirPlaneta(planeta);
		
	}
	
	@Test
	public void listarPlanetasTest() {
		Mockito.when(repository.findAll()).thenReturn(CriarListaPlanetas());
		List<Planeta> listaPlanetasRepo = service.listarPlanetas();
		Assert.assertNotNull(listaPlanetasRepo);
		
		Mockito.when(repository.findAll()).thenReturn(new ArrayList<Planeta>());
		exception.expect(ObjetoNaoEncontradoException.class);
		service.listarPlanetas();
		
	}
	
	@Test
	public void buscarPorNomeTest() {
		Planeta planeta = criarInstanciaPlaneta();
		Mockito.when(repository.findByNomePlanetaContaining("Tatooine")).thenReturn(CriarListaPlanetas());
		Planeta planetaAtual = service.buscarPorNome("Tatooine");
		Assert.assertEquals(planeta.getNomePlaneta(), planetaAtual.getNomePlaneta());
		
		Mockito.when(repository.findByNomePlanetaContaining("Nome fora da lista")).thenReturn(new ArrayList<Planeta>());
		exception.expect(ObjetoNaoEncontradoException.class);
		service.buscarPorNome("Nome fora da lista");
	}
	
	@Test
	public void buscarPorIdTest() {
		Planeta planeta = criarInstanciaPlaneta();
		Mockito.when(repository.findByPlanetaId("1")).thenReturn(planeta);
		Planeta planetaAtual = service.buscarPorId("1");
		Assert.assertEquals(planeta.getNomePlaneta(), planetaAtual.getNomePlaneta());
		
		Mockito.when(repository.findByPlanetaId("1")).thenReturn(null);
		exception.expect(ObjetoNaoEncontradoException.class);
		service.buscarPorId("1");
	}
	
	@Test
	public void deletarPlanetaTest() {
		Planeta planeta = criarInstanciaPlaneta();
		Mockito.when(repository.findByPlanetaId("1")).thenReturn(planeta);
		service.deletarPlaneta("1");
		
		Mockito.when(repository.findByPlanetaId("1")).thenReturn(null);
		exception.expect(ObjetoNaoEncontradoException.class);
		service.deletarPlaneta("1");
	}

}
