package com.project.bit.projects.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bit.projects.domain.Users;
import com.project.bit.projects.mapper.UserMapper;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;

	public MyUserDetailService() {

		// TODO Auto-generated constructor stub
	}

	@Override
	public UserDetails loadUserByUsername(String USER_ID) throws UsernameNotFoundException {

		Users member = userMapper.selectUserById(USER_ID).orElseThrow(() -> new UsernameNotFoundException(USER_ID));
		System.out.println(member);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		if (member.getDutyCode().equals("duty100")) {
			grantedAuthorities.add(new SimpleGrantedAuthority(member.getDutyCode()));
		} else if (member.getDutyCode().toString().equals("duty200")) {
			grantedAuthorities.add(new SimpleGrantedAuthority(member.getDutyCode()));
		} else if (member.getDutyCode().toString().equals("duty300")) {
			grantedAuthorities.add(new SimpleGrantedAuthority(member.getDutyCode()));
		} else if (member.getDutyCode().toString().equals("duty400")) {
			grantedAuthorities.add(new SimpleGrantedAuthority(member.getDutyCode()));
		}
		
		User user = new User(member.getUserName(), member.getUserPw(), grantedAuthorities);
		return user;

	}

	@Transactional
	public void joinUser(Users users) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		users.setUserPw(passwordEncoder.encode(users.getUserPw()));

	}

}