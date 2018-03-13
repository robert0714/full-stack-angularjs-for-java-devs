package com.apress.ravi.chapter2.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apress.ravi.chapter2.dto.UserInfo;
import com.apress.ravi.chapter2.repository.UserInfoJpaRepository;

@Service
public class UserInfoDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoJpaRepository userInfoJpaRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		UserInfo user = userInfoJpaRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(
					"Opps! user not found with user-name: " + username);
		}

		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), getAuthorities(user));
	}

	private Collection<GrantedAuthority> getAuthorities(UserInfo user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities = AuthorityUtils.createAuthorityList(user.getRole());
		return authorities;
	}
}
