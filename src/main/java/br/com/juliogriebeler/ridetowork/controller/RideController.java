/**
 * 
 */
package br.com.juliogriebeler.ridetowork.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.juliogriebeler.model.Ride;
import br.com.juliogriebeler.repository.RideRepository;
import br.com.juliogriebeler.ridetowork.exception.RideException;

/**
 * @author juliofg
 *
 */

@RestController
@RequestMapping("/api")
public class RideController {

	@Autowired
	RideRepository rideRepository;

	@PostMapping("/ride")
	@CrossOrigin
	public Ride save(@Valid @RequestBody Ride ride, @RequestParam("dishImage") MultipartFile dishImage) {
		if (dishImage.isEmpty()) {
			System.out.println("EMPTY IMAGE");
		} else {
			byte[] bytes;
			String fileName = dishImage.getOriginalFilename();
			try {
				bytes = dishImage.getBytes();
				Path path = Paths.get(fileName);
				Files.write(path, bytes);
				ride.setDishImage(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rideRepository.save(ride);
	}

	@GetMapping("/rides")
	@ResponseBody
	@CrossOrigin
	public List<Ride> findAll(Model model) {
		if(model.containsAttribute("isVegetarian")) {
			System.out.println("IS VEG");
			return rideRepository.findByIsVegetarian(Boolean.TRUE);
		}
		System.out.println("NOT VEG");
		return rideRepository.findAll();
	}

	@GetMapping("/ride/{id}")
	@ResponseBody
	@CrossOrigin
	public Ride findById(@PathVariable(value = "id") String rideId) {
		return rideRepository.findById(rideId)
				.orElseThrow(() -> new RideException("Ride", "id", rideId));
	}
	
	@DeleteMapping("/ride/{id}")
	@ResponseBody
	public String delete(@PathVariable(value = "id") String rideId) {
		try {
			rideRepository.deleteById(rideId);
		} catch (Exception e) {
			return "Error deleting ride with ID " + rideId;
		}
		return "Ride deleted!";
	}
}