/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entities.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.model.JwtRequest;
import com.main.sec.JwtTokenUtil;
import com.main.sec.JwtUserDetailsService;
import com.model.BranchModel;
import com.model.CompanyModel;
import com.model.UserModel;
import com.repository.JdbcBranchRepository;
import com.repository.JdbcCompanyRepository;
import com.util.Util;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.repository.UserDataRepository;
@RestController
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder bcryptEncoder;        

        @Autowired
        private UserDataRepository userDataRepo;
        @Autowired
        private JdbcCompanyRepository companyRepo;
        @Autowired
        private JdbcBranchRepository branchRepo;
        
        @CrossOrigin( origins = "*" )        
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		//return ResponseEntity.ok(new JwtResponse(token));

                UserModel aUserModel = new UserModel();
                //UserDataCtrl cUserData = new UserDataCtrl();
                String userid = authenticationRequest.getUsername(); 
                //UserData aUserData = cUserData.Select(userid);
                UserData aUserData = this.userDataRepo.select(userid);
                if (aUserData==null) {
                    //Not found
                    aUserModel.setRespMsg("Not found");
                    return new ResponseEntity<>(aUserModel,HttpStatus.OK);        
                }
                
                //Company aCompany = this.companyRepo.select(aUserData.getCompCode());
                CompanyModel aCompany = this.companyRepo.findByPK(aUserData.getCompCode());
                if (aCompany==null) {
                    aUserModel.setRespMsg("Not found company");
                    return new ResponseEntity<>(aUserModel,HttpStatus.OK);        
                }
                //BranchCtrl cBranch = new BranchCtrl();
//                BranchPK kBranch = new BranchPK();
//                kBranch.setCompCode(aUserData.getCompCode());
//                kBranch.setBranchCode(aUserData.getBranchCode());
                //Branch aBranch = cBranch.Select(kBranch);
                //Branch aBranch = this.branchRepo.findByBranchPK(kBranch);
                BranchModel aBranch = this.branchRepo.findByPK(aUserData.getCompCode(),aUserData.getBranchCode());
                if (aBranch==null) {
                    aUserModel.setRespMsg("Not found branch");
                    return new ResponseEntity<>(aUserModel,HttpStatus.OK);        
                }
                
                aUserModel.setUserId(aUserData.getUserId());
                aUserModel.setUserFname(aUserData.getUserFname());
                aUserModel.setUserLname(aUserData.getUserLname());
                aUserModel.setToken(token);
                if (aCompany.getUseSystemDate().equals("Y")) {
                    aCompany.setTodayDate(Util.TodayDate("yyyy-MM-dd"));
//                    if (!cCompany.update(aCompany)) {
//                        aUserModel.setRespMsg("Error update Company");
//                        return new ResponseEntity<>(aUserModel,HttpStatus.OK);        
//                    }
                    this.companyRepo.update(aCompany);
                }
                aUserModel.setTodayDate(aCompany.getTodayDate());
                aUserModel.setCompCode(aCompany.getCompCode());
                aUserModel.setCompName(aCompany.getNameTh());
                aUserModel.setBranchCode(aBranch.getBranchCode());
                aUserModel.setBranchName(aBranch.getNameTh());
                
                aUserModel.setRespMsg("Completed");

                return new ResponseEntity<>(aUserModel,HttpStatus.OK);  
	}
        
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
//		return ResponseEntity.ok(userDetailsService.save(user));
//	}
        
        @CrossOrigin( origins = "*" )        
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserModel user) throws Exception {
            // Add UserData  UserID = Input email
            //UserDataCtrl cUserData = new UserDataCtrl();
            //UserData aUserData = cUserData.Select(user.getUserId());
            UserData aUserData = this.userDataRepo.select(user.getUserId());
            if (aUserData!=null) {
                // Error
                user.setRespMsg("Error already exist");
                return new ResponseEntity<>(user,HttpStatus.OK);        
            }
            aUserData = new UserData();
            aUserData.setUserId(user.getUserId());
            aUserData.setUserFname(user.getUserFname());
            aUserData.setUserLname(user.getUserLname());
            aUserData.setPassword(bcryptEncoder.encode(user.getPassword()));
//            if (!cUserData.add(aUserData)) {
//                // Error
//                user.setRespMsg("Error add user");
//                return new ResponseEntity<>(user,HttpStatus.OK);        
//            }
            this.userDataRepo.save(aUserData);
            user.setRespMsg("Completed");
            
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("token", "1234567890");            
//            return new ResponseEntity<>("Completed",headers,HttpStatus.OK);        
            return new ResponseEntity<>(user,HttpStatus.OK);        
            //return ResponseEntity.ok(userDetailsService.save(user));
	}        
        
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}


}
