package com.example.inventorym.config;

import com.example.inventorym.entity.User;
import com.example.inventorym.entity.UserToken;
import com.example.inventorym.entity.enums.Role;
import com.example.inventorym.repository.UserRepository;
import com.example.inventorym.repository.UserTokenRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.UUID;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;

    private UUID id;

    // other fields, getters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CustomUserDetailsService(UserRepository userRepository, UserTokenRepository userTokenRepository) {
        this.userRepository = userRepository;
        this.userTokenRepository = userTokenRepository;
    }



/*    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));


        UserToken userToken = userTokenRepository.findByUserId(user.getId());
        if (userToken == null) {
            userToken = new UserToken();
        }
        userToken.setToken(generateToken());
        userToken.setUser(new User(user.getId()));
        userTokenRepository.save(userToken);
        return new CustomUserDetails(user, getAuthorities(user));
    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email " + username + " not found"));

        UserToken userToken = userTokenRepository.findByUserId(user.getId());
        if (userToken == null) {
            userToken = new UserToken();
        }
        userToken.setToken(generateToken());
        userToken.setUser(new User(user.getId()));
        userTokenRepository.save(userToken);

        return new UserPrincipal(user);
    }
    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles().stream().map(Role::name).toArray(String[]::new);
        return AuthorityUtils.createAuthorityList(userRoles);
    }

    private String generateToken() {
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        UserToken userToken = userTokenRepository.findByToken(token);
        if (userToken != null) {
            return generateToken();
        }
        return token;
    }
}
