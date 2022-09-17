package com.controller;

import com.model.RespModel;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloWorldController {
//	@RequestMapping({ "/hello" })
//	public String firstPage() {
//		return "Hello World";
//	}
        
	@RequestMapping({ "/hello" })
	public RespModel firstPage() {
            RespModel resp = new RespModel();
            resp.setCompCode("AAAAAAA");
            resp.setRespMsg("11111111");
            resp.setRespStatus("Completed");
            
            return resp;
	}

}