package org.ogm.gke.gkedemo;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Metrics;

@RestController("/")
public class HelloController {

	@RequestMapping("/")
	public ResponseEntity<String> initial() {
		return new ResponseEntity<>("now is " + new Date(), HttpStatus.OK);
	}
	
	@RequestMapping("/hola")
	public String sayHello(@RequestParam(value="name") String name) {
		Metrics.counter("Hola ", "name", getClass().getSimpleName());
		return "Hola " + name;
	}
}
