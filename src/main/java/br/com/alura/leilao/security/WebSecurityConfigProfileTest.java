package br.com.alura.leilao.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@Profile("test")
public class WebSecurityConfigProfileTest extends WebSecurityConfigurerAdapter {
	
	@Autowired DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/db/**").permitAll()	
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers("/leiloes").permitAll()
			.antMatchers("/css/**").permitAll();;
        
		http.authorizeRequests().anyRequest().authenticated();

		
		http.formLogin(form -> form
            .loginPage("/login")
            .defaultSuccessUrl("/leiloes", true)
            .permitAll()
        );
		
		http.logout(logout -> {
			logout.logoutUrl("/logout")
				.logoutSuccessUrl("/leiloes");
		});
		
		http.headers().frameOptions().disable();
		http.csrf().disable();
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		BCryptPasswordEncoder enconder = passwordEncoder();
//		auth.inMemoryAuthentication().passwordEncoder(enconder)
//			.withUser("fulano").password(enconder.encode("pass")).roles("USER")
//			.and()
//			.withUser("cigano").password(enconder.encode("pass")).roles("USER")
//			.and()
//			.withUser("beltrano").password(enconder.encode("pass")).roles("USER");
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
	
	
	
}
