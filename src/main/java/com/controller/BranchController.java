package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.entities.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.BranchModel;
import com.repository.JdbcBranchRepository;

import com.util.Util;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/branch")
@Log4j2
public class BranchController {
    @Autowired
    private JdbcBranchRepository branchRepository;
    
    @PostMapping("/add")
    @ResponseBody   
    public ResponseEntity<BranchModel> add(HttpServletRequest request,@RequestBody BranchModel branch) {
        try {
            
            BranchModel object = this.branchRepository.findByPK(branch.getCompCode(),branch.getBranchCode());
            if (object!=null) {
                branch.setRespMsg("Error dupplicate");
                return new ResponseEntity<>(branch,HttpStatus.OK);        
            }     
            //Add
            this.branchRepository.add(branch);
            
            branch.setRespMsg("Completed");
            return new ResponseEntity<>(branch,HttpStatus.OK);        
            
            
        } catch (Exception ex) {
            branch.setRespMsg("Error");
            return new ResponseEntity<>(branch,HttpStatus.OK);        
        }
        
    } 
    
    @PostMapping("/update")
    @ResponseBody   
    public ResponseEntity<BranchModel> update(HttpServletRequest request,@RequestBody BranchModel branch) {
//        ActivityLogModel aActivitylog = new ActivityLogModel();
        try {
            this.branchRepository.update(branch);
            branch.setRespMsg("Completed");
            return new ResponseEntity<>(branch ,HttpStatus.OK);        
        } catch (Exception ex) {
//           
            branch.setRespMsg("Error");
            return new ResponseEntity<>(null ,HttpStatus.OK);        
        }
    }
    
    @PostMapping("/delete")
    @ResponseBody   
    public ResponseEntity<BranchModel> delete(HttpServletRequest request,@RequestBody BranchModel branch) {
        try {
            BranchModel object = this.branchRepository.findByPK(branch.getCompCode(),branch.getBranchCode());
            if (object==null) {
                //Not found
                branch.setRespMsg("Error: Not found");
                return new ResponseEntity<>(branch,HttpStatus.OK);        
            }
            this.branchRepository.delete(branch.getCompCode(),branch.getBranchCode());
            branch.setRespMsg("Completed");
            
            return new ResponseEntity<>(branch,HttpStatus.OK);        
        } catch (Exception ex) {
            branch.setRespMsg("Error");
            return new ResponseEntity<>(branch,HttpStatus.OK);        
        }
        
    }

    @PostMapping(value = "/select")
    @ResponseBody
    public ResponseEntity<BranchModel> Select(HttpServletRequest request,@RequestBody BranchModel branch) {
        try {
            BranchModel object = this.branchRepository.findByPK(branch.getCompCode(),branch.getBranchCode());
            if (object==null) {
                //Not found
                branch.setRespMsg("Error: Not found");
                return new ResponseEntity<>(branch,HttpStatus.OK);        
            }
            branch = object;
            branch.setRespMsg("Completed");            
            return new ResponseEntity<>(branch,HttpStatus.OK);        
        }
        catch (Exception ex) {
            branch.setRespMsg("Error");
            return new ResponseEntity<>(branch,HttpStatus.OK);        
        }
         
    }
    
    @GetMapping(value = "/selectbycompcode")
    @ResponseBody
    public ResponseEntity<List> SelectByCompCode(HttpServletRequest request,@RequestParam String compcode) {
        try {
            List list = this.branchRepository.findByCompCode(compcode); 
            
            return new ResponseEntity<>(list,HttpStatus.OK);        
        }
        catch (Exception ex) {
            return null;
        }
    }

}
