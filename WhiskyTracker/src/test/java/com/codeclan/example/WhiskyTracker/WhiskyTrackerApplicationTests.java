package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;


	@Test
	public void contextLoads() {
	}

	@Test
	public void createWhiskyAndDistillery() {
		Distillery glendronach = new Distillery("Glendronach", "Highland");
		distilleryRepository.save(glendronach);
		Whisky glendronachRevival = new Whisky("The Glendronach Revival", 15, 2018, glendronach);
		whiskyRepository.save(glendronachRevival);
	}

	@Test
	public void canFindWhiskiesMadeIn2018() {
		List<Whisky> found = whiskyRepository.findByYear(2018);
	}

	@Test
	public void canFindDistilleriesFromSpeyside() {
		List<Distillery> found = distilleryRepository.findByRegion("Speyside");
		assertEquals("Speyside", found.get(0).getRegion());
	}

	@Test
	public void canFindWhiskyFromDistilleryByAge() {
		Distillery distillery13 = new Distillery();
		distilleryRepository.save(distillery13);
		Whisky founderReserve = new Whisky("Founder's Reserve", 12, 2014, distillery13);
		whiskyRepository.save(founderReserve);

	}
}
