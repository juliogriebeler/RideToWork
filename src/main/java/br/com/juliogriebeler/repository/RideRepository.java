package br.com.juliogriebeler.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.juliogriebeler.model.Ride;

public interface RideRepository extends MongoRepository<Ride, String>{
	
	@Query("{isVegetarian : ?0}")
	public List<Ride> findByIsVegetarian(Boolean isVegetarian);
	
}