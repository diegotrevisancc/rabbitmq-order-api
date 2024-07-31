package dev.trevisan.order.api.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI OpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Orders REST API")
                                .description("API to create new orders")
                                .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                                .contact(new Contact().name("Diego Trevisan").url("https://www.linkedin.com/in/diego-trevisan-cc/"))


                );
    }
    
}
