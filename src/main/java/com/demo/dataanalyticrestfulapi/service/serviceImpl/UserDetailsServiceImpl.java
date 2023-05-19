package com.demo.dataanalyticrestfulapi.service.serviceImpl;

import com.demo.dataanalyticrestfulapi.Reposity.UserRepository;
import com.demo.dataanalyticrestfulapi.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;
    UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println(" loadUserByUsername is working jsutr fine  ");
        System.out.println("Username is : "+username);
        User authenticatedUser = userRepository.findUserByUsername(username).stream().findFirst().orElse(null);

        System.out.println("Here is the authenticatedUser: " + authenticatedUser);
        if (authenticatedUser== null ) {
            throw new UsernameNotFoundException("Authenticated User doesn't exist !! ");
        }

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) org.springframework.security.core.userdetails.User.builder()
                .username(authenticatedUser.getUsername())
                .password(authenticatedUser.getPassword())
                .roles("USER").build();
        return user ;
    }
}
