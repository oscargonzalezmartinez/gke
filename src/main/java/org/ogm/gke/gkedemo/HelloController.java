package org.ogm.gke.gkedemo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Metrics;

@RestController("/")
public class HelloController {

	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	@RequestMapping("/")
	public ResponseEntity<String> initial() {
		Metrics.counter("initial").increment();
		try {
			logger.info(InetAddress.getLocalHost().getCanonicalHostName());
		} catch (UnknownHostException e) {
			logger.error("initial", e);
		}
		return new ResponseEntity<>("now is " + new Date(), HttpStatus.OK);
	}
	
	@RequestMapping("/hola")
	public String sayHello(@RequestParam(value="name") String name) {
		return "Hola " + name;
	}
	
	@RequestMapping("/doerror")
	public ResponseEntity<String> error() {
		RuntimeException rte = new RuntimeException("Un error");
		logger.error("initial", rte);
		throw rte;
	}
}
