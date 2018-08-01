package br.com.juliogriebeler.ridetowork;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.juliogriebeler.ridetowork.properties.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class RideToWorkApp {

	public static void main(String[] args) {
		SpringApplication.run(RideToWorkApp.class, args);
	}
}
