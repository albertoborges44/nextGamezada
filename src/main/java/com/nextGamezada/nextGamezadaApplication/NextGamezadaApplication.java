package com.nextGamezada.nextGamezadaApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(scanBasePackages = "com.nextGamezada")
public class NextGamezadaApplication {

	@RequestMapping
	String hello() {
		return "Hello Next Gamezada Application";
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(NextGamezadaApplication.class).headless(false).run(args);
	}

}
