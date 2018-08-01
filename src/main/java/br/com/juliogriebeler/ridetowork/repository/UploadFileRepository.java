package br.com.juliogriebeler.ridetowork.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.juliogriebeler.ridetowork.model.UploadFile;

public interface UploadFileRepository extends MongoRepository<UploadFile, String>{	
}