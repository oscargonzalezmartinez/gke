package org.ogm.gke.gkedemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Metrics;

@RestController("/")
public class HelloController {

	
	@RequestMapping("/hola")
	public String sayHello(@RequestParam(value="name") String name) {
		Metrics.counter("Hola ", "name", getClass().getSimpleName());
		return "Hola " + name;
	}
}
