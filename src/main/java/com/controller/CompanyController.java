package com.controller;

import com.model.CompanyModel;
import com.repository.JdbcCompanyRepository;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/company")
@Log4j2
public class CompanyController {
    @Autowired
    private JdbcCompanyRepository companyRepository;

    @PostMapping("/add")
    @ResponseBody   
    public ResponseEntity<CompanyModel> add(HttpServletRequest request,@RequestBody CompanyModel comp) {
        try {
            CompanyModel object = this.companyRepository.findByPK(comp.getCompCode());
            if (object!=null) {
                comp.setRespMsg("Error dupplicate");
                return new ResponseEntity<>(comp,HttpStatus.OK);        
            }     
            //Add
            this.companyRepository.add(comp);
            
            comp.setRespMsg("Completed");
            return new ResponseEntity<>(comp,HttpStatus.OK);        
            
        } catch (Exception ex) {
            comp.setRespMsg("Error");
            return new ResponseEntity<>(comp,HttpStatus.OK);        
        }
        
    }
    
    @PostMapping("/update")
    @ResponseBody   
    public ResponseEntity<CompanyModel> update(HttpServletRequest request,@RequestBody CompanyModel comp) {
        try {
            this.companyRepository.update(comp);
            comp.setRespMsg("Completed");
            return new ResponseEntity<>(comp ,HttpStatus.OK);        
        } catch (Exception ex) {
            comp.setRespMsg("Error");
            return new ResponseEntity<>(null ,HttpStatus.OK);        
        }
    }
    
    @PostMapping("/delete")
    @ResponseBody   
    public ResponseEntity<CompanyModel> delete(HttpServletRequest request,@RequestBody CompanyModel comp) {
        try {
            
            CompanyModel object = this.companyRepository.findByPK(comp.getCompCode());
            if (object==null) {
                //Not found
                comp.setRespMsg("Error: Not found");
                return new ResponseEntity<>(comp,HttpStatus.OK);        
            }
            this.companyRepository.delete(comp.getCompCode());
            comp.setRespMsg("Completed");
            
            return new ResponseEntity<>(comp,HttpStatus.OK);        
        } catch (Exception ex) {
            comp.setRespMsg("Error");
            return new ResponseEntity<>(comp,HttpStatus.OK);        
        }
        
    }
    
    
    @PostMapping(value = "/select")
    @ResponseBody
    public ResponseEntity<CompanyModel> Select(HttpServletRequest request,@RequestBody CompanyModel comp) {
        try {
            CompanyModel object = this.companyRepository.findByPK(comp.getCompCode());
            if (object==null) {
                //Not found
                comp.setRespMsg("Error: Not found");
                return new ResponseEntity<>(comp,HttpStatus.OK);        
            }
            comp = object;
            comp.setRespMsg("Completed");            
            return new ResponseEntity<>(comp,HttpStatus.OK);        
        }
        catch (Exception ex) {
            comp.setRespMsg("Error");
            return new ResponseEntity<>(comp,HttpStatus.OK);        
        }
         
    }
    @GetMapping(value = "/searchall")
    @ResponseBody
    public ResponseEntity<List> SelectAll(HttpServletRequest request) {
        try {
            List list = this.companyRepository.findAll(); 
            
            return new ResponseEntity<>(list,HttpStatus.OK);        
        }
        catch (Exception ex) {
            return null;
        }        
    }
    @GetMapping(value = "/searchbycompcode")
    @ResponseBody
    public ResponseEntity<List> SearchByCompCode(HttpServletRequest request,@RequestParam String compcode) {
        try {
            
            List list = this.companyRepository.findByCompCode(compcode); 
            
            return new ResponseEntity<>(list,HttpStatus.OK);        
        }
        catch (Exception ex) {
            return null;
        }        
    }
    @GetMapping(value = "/searchbycompname")
    @ResponseBody
    public ResponseEntity<List> SearchByCompName(HttpServletRequest request,@RequestParam String compname) {
        try {
            
            List list = this.companyRepository.findByCompName(compname); 
            
            return new ResponseEntity<>(list,HttpStatus.OK);        
        }
        catch (Exception ex) {
            return null;
        }        
    }


}
