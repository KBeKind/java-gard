//package com.example.javagarden.security;
//
////import com.example.javagarden.data.UserRepository;
//import com.example.javagarden.models.Role;
//import com.example.javagarden.models.UserEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
////    private UserRepository userRepository;
//
////    public CustomUserDetailsService(UserRepository userRepository) {
////        this.userRepository = userRepository;
////    }
//
//    public CustomUserDetailsService() {}
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//        return new User(userEntity.getUsername(), userEntity.getPwHash(), mapRolesToAuthorities(userEntity.getRoles()));
//    }
//
//    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }
//
//
//}
