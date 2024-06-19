  package usj.master.iotdigitalpayment;

  import org.springframework.context.annotation.Configuration;
  import org.springframework.web.cors.CorsConfiguration;
  import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
  import org.springframework.web.filter.CorsFilter;
  import org.springframework.security.config.annotation.web.builders.HttpSecurity;
  import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
  import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
  import org.springframework.context.annotation.Bean;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/usjMaster/api/transaction/**").anonymous()
            .and()
            .httpBasic()
            .and()
            .cors();// or any other authentication mechanism
    }
	  @Bean
	  public CorsFilter corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.addAllowedOrigin("*"); // Allow all origins
	        config.addAllowedHeader("*"); // Allow all headers
	        config.addAllowedMethod("*"); // Allow all methods
	        source.registerCorsConfiguration("/**", config);
	        return new CorsFilter(source);
	    }
}
