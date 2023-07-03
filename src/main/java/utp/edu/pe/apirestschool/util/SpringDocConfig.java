package utp.edu.pe.apirestschool.util;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI gobenefitsOpenApi() {
        return new OpenAPI().info(new Info().title("API REST School").version("v1.0"));
    }

    private static final List<String> PERMIT_ALL_PATHS =
            List.of(
                    "/login",
                    "/usuarios/login");

    static {
        SpringDocUtils.getConfig().addAnnotationsToIgnore(CurrentUser.class);
    }


}
