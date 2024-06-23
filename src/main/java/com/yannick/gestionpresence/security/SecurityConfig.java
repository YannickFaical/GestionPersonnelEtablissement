package com.yannick.gestionpresence.security;




public class SecurityConfig {

////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests()
////                .antMatchers("/admin/**").hasRole("ADMIN")
////                .antMatchers("/user/**").hasRole("USER")
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                .permitAll()
////                .and()
////                .logout()
////                .logoutSuccessUrl("/")
////                .permitAll()
////                .and()
////                .sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
////                .maximumSessions(1);
////
////        return http.build();
////    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.formLogin().defaultSuccessUrl("/");
//        httpSecurity.authorizeHttpRequests().requestMatchers("/").permitAll();
//        httpSecurity.authorizeHttpRequests().requestMatchers("/clts").hasRole("USER");
//        httpSecurity.authorizeHttpRequests().requestMatchers("/**ajout**").hasRole("ADMIN");
//        httpSecurity.authorizeHttpRequests().requestMatchers("/**save**").hasRole("ADMIN");
//        httpSecurity.authorizeHttpRequests().requestMatchers("/**edit**").hasRole("ADMIN");
//        httpSecurity.authorizeHttpRequests().requestMatchers("/**update**").hasRole("ADMIN");
//        httpSecurity.authorizeHttpRequests().requestMatchers("/**delete**").hasRole("ADMIN");
//        httpSecurity.authorizeHttpRequests().requestMatchers("/**supprimer**").hasRole("ADMIN");
//        httpSecurity.authorizeHttpRequests().requestMatchers("/**formFacture**").hasRole("ADMIN");
//        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
//        return httpSecurity.build();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin").password("{noop}password").roles("ADMIN")
//                .and()
//                .withUser("user").password("{noop}password").roles("USER");
//    }
}
