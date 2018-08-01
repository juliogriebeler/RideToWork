package br.com.juliogriebeler.ridetowork;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.juliogriebeler.ridetowork.model.Ride;
import br.com.juliogriebeler.ridetowork.repository.RideRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RideToWorkAppTest {

	@MockBean
	RideRepository rideRepositoryMock;
	
	@Test
	public void saveRideTest(){
		Ride r = createTestRide();
		assertEquals(rideRepositoryMock.save(r), r);
	}
	

	private Ride createTestRide(){
		Ride r = new Ride();	
		return r;
	}

}
