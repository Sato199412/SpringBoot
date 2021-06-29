package jp.co.cybermissions.itspj.java.bbs.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.cybermissions.itspj.java.bbs.models.CustomUser;
import jp.co.cybermissions.itspj.java.bbs.models.CustomUserDetailsImpl;
import jp.co.cybermissions.itspj.java.bbs.models.CustomUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final CustomUserRepository rep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomUser user = rep.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + "not found");
        }
        return new CustomUserDetailsImpl(user);
    }
}
