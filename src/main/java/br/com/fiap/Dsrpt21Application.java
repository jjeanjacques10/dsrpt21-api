package br.com.fiap;

import org.apache.catalina.Context;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Dsrpt21Application {

	public static void main(String[] args) {
		SpringApplication.run(Dsrpt21Application.class, args);
	}
	
	@Bean
	public TomcatServletWebServerFactory tomcatFactory() {
		return new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
			}
		};
	}
}
