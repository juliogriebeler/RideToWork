package br.com.juliogriebeler.ridetowork.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.juliogriebeler.ridetowork.model.Ride;

public interface RideRepository extends MongoRepository<Ride, String>{	
}