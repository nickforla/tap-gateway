package tap.nforla.tapgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class TapGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TapGatewayApplication.class, args);
	}

	@Bean
	public AuthorizationFilter authorizationFilter(){
		return new AuthorizationFilter();
	}
}
