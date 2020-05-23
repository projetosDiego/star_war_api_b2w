package com.b2w.starwars.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.b2w.starwars.model.Planeta;

public interface IPlanetaRepository extends MongoRepository<Planeta, Long>{

	Planeta       findByPlanetaId(String id);
	List<Planeta> findByNomePlanetaContaining(String nome);

}
