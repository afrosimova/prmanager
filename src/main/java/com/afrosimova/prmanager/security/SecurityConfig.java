package com.afrosimova.prmanager.security;

import com.afrosimova.prmanager.views.employee.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity // <1>
@Configuration
public class SecurityConfig extends VaadinWebSecurity { // <2>

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/images/*.png").permitAll();  // <3>
        super.configure(http);
        setLoginView(http, LoginView.class); // <4>
    }

//    @Bean
//    public UserDetailsService users() {
//    //    return userService;
//        UserDetails user = User.builder()
//                .username("user")
//                // password = password with this hash, don't tell anybody :-)
//                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin); // <5>
//    }
}
