/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.sec;

import com.entities.UserData;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.repository.UserDataRepository;
@Service
public class JwtUserDetailsService implements UserDetailsService {
        @Autowired
        private UserDataRepository userDataRepo;
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		if ("javainuse".equals(username)) {
//			return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//					new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
//	}
        
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		DAOUser user = userDao.findByUsername(username);
//		if (user == null) {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//				new ArrayList<>());
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//DAOUser user = userDao.findByUsername(username);
                //UserDataCtrl cUserData = new UserDataCtrl();
                String userid = username; // userId = username from input
//                UserData aUserData = cUserData.Select(userid);
                UserData aUserData = this.userDataRepo.select(userid);
                if (aUserData==null) {
                    throw new UsernameNotFoundException("User not found with username: " + username);
                }
		return new org.springframework.security.core.userdetails.User(aUserData.getUserId(), aUserData.getPassword(),
				new ArrayList<>());
                
                
	}        
        
}