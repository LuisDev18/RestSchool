package utp.edu.pe.ApiRestSchool;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(

				title="Spring Boot REST API Documentation",
				description="Spring Boot REST API School demo",
				version="v1.0",
				contact=@Contact(
						name="Luis B. - Miguel G. - Miguel T. Anthony - Heraud M.",
						email="developers-students@utp.pe",
						url="https://github.com/LuisDev18?tab=repositories"
				),
				license=@License(
						name="Apache 2.0",
						url="https://httpd.apache.org/"
				)
		)
)
public class UtpRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtpRestApplication.class, args);
	}

}
