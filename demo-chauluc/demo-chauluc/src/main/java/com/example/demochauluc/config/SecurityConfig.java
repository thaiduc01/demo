package com.example.demochauluc.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demochauluc.entities.auth.Role;
import com.example.demochauluc.entities.auth.User;
import com.example.demochauluc.repository.auth.RoleRepository;
import com.example.demochauluc.repository.auth.UserRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtAuthenthicationFilter jwtAuthenthicationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    
    @Autowired
    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAuthenthicationFilter jwtAuthenthicationFilter,
            RoleRepository roleRepository, UserRepository userRepository) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAuthenthicationFilter = jwtAuthenthicationFilter;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.checkRolesInDataBase();
    }
    
    
    @Bean 
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http 
                .csrf()
                .disable().exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/chauluc").permitAll()
                .antMatchers("/api/quocgia").permitAll()
                .antMatchers("/api/dantoc").permitAll()
                .antMatchers("/api/ngonngu").permitAll()
                .antMatchers("/manage/login").permitAll()
                .antMatchers("/manage/register").permitAll()
                .antMatchers("/api/authenticate").permitAll()
                .and()
                .httpBasic();
        http.addFilterBefore(jwtAuthenthicationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
        
    }
    
    
    @Bean 
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    @Bean
    public void createDefaultAdminUser() {
        PasswordEncoder passwordEncoder = passwordEncoder();
        if(!userRepository.existsByEmail("admin123@gmail.com")) {
            User defaulAdmin = new User();
            defaulAdmin.setName("admin");
            defaulAdmin.setEmail("admin123@gmail.com");
            defaulAdmin.setMobileNumber("0987676767");
            defaulAdmin.setPassword(passwordEncoder.encode("admin123"));
//            defaulAdmin.setRoles(new ArrayList<>());
//            Role role = roleRepository.findByRole("ROLE_ADMIN")
//                    .orElseThrow(()-> new RuntimeException("Role không tồn tại"));
//            defaulAdmin.getRoles().add(role);
//            userRepository.save(defaulAdmin);
            defaulAdmin.setRoles(Collections.singletonList(getAdminRole()));
            userRepository.save(defaulAdmin);
        }
        //return passwordEncoder;
    }
    
    private Role getAdminRole() {
        return roleRepository.findByRole("ROLE_ADMIN").
                orElseThrow(()-> new RuntimeException("Role không tồn tại"));
    }
    
    
    public void checkRolesInDataBase() {
        List<String> roles = Arrays.asList("ROLE_ADMIN","ROLE_USER");
        for(String i:roles) {
            if(!roleRepository.existsByRole(i)) {
                Role role = new Role();
                role.setRole(i);
                roleRepository.save(role); 
            }
           
        }
    }
}
