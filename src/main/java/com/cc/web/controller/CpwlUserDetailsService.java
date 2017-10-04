package com.cc.web.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;




//import org.slf4j.Logger;  
//import org.slf4j.LoggerFactory;  
//import org.springframework.beans.factory.annotation.Autowired;  
//import org.springframework.dao.EmptyResultDataAccessException;  
//import org.springframework.jdbc.core.JdbcTemplate;  
//import org.springframework.jdbc.core.RowMapper;  
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CpwlUserDetailsService implements UserDetailsService {

	// @Autowired
	// JdbcTemplate jdbcTemplate;
	private final String sqlLoadUser;
	private final String sqlLoadAuthorities;

	// private final RowMapper<User> myUserDetailsRowMapper;
	// private final RowMapper<GrantedAuthority> authorityRowMapper;
	//
	// private static Logger logger = LoggerFactory
	// .getLogger(CpwlUserDetailsService.class);

	public CpwlUserDetailsService() {
		super();
		sqlLoadUser = "SELECT id,username,password,enabled FROM user WHERE username=? OR phoneNumber=? OR email=?";
		sqlLoadAuthorities = "SELECT authority FROM view_role WHERE username=?";
		// myUserDetailsRowMapper = new RowMapper<User>() {
		// @Override
		// public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		// return new User(rs.getLong(1), rs.getString(2),
		// rs.getString(3), rs.getBoolean(4));
		// }
		// };
		// authorityRowMapper = new RowMapper<GrantedAuthority>() {
		// @Override
		// public GrantedAuthority mapRow(ResultSet rs, int rowNum)
		// throws SQLException {
		// return new SimpleGrantedAuthority(rs.getString(1));
		// }
		// };
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

		GrantedAuthority ga = new GrantedAuthority(){

			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				return "ROLE_USER";
			}};
		List list = new ArrayList<GrantedAuthority>();
		list.add(ga);
		User user = new User(1L, username, encoder.encode(username), true,list);
		
		return user;
		// try {
		// User userFromQuery = jdbcTemplate.queryForObject(sqlLoadUser,
		// myUserDetailsRowMapper, username, username, username);
		// logger.debug("查询得到用户：{}", userFromQuery);
		// List<GrantedAuthority> authorities = jdbcTemplate.query(
		// sqlLoadAuthorities, authorityRowMapper, username);
		// logger.debug("得到其权限：{}", authorities);
		// return new User(userFromQuery.getId(), userFromQuery.getUsername(),
		// userFromQuery.getPassword(), userFromQuery.isEnabled(),
		// authorities);
		// } catch (EmptyResultDataAccessException e) {
		// logger.debug("查询结果集为空:{}", username);
		// throw new UsernameNotFoundException("用户名或密码不正确");
		// }
	}

	static void main(String[] args) {

	}

}