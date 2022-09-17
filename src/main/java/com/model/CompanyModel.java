package com.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CompanyModel implements Serializable {
    private String compCode;
    private String nameTh;
    private String nameEn;
    private String addr1;
    private String addr2;
    private String addr3;
    private String zip;
    private String tel;
    private String fax;
    private String email;
    private String webSite;
    private String regId;
    private String regDate;
    private Double vatRate;
    private String todayDate;
    private String useSystemDate;
    private String updateBy;
    private String updateDate;
    private String updateTime;
    int version;
    
    // API response message
    private String respMsg;

    public CompanyModel() {
    }

    public CompanyModel(String compCode, String nameTh, String nameEn, String addr1, String addr2, String addr3, String zip, String tel, String fax, String email, String webSite, String regId, String regDate, Double vatRate, String todayDate, String useSystemDate, String updateBy, String updateDate, String updateTime, int version) {
        this.compCode = compCode;
        this.nameTh = nameTh;
        this.nameEn = nameEn;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.addr3 = addr3;
        this.zip = zip;
        this.tel = tel;
        this.fax = fax;
        this.email = email;
        this.webSite = webSite;
        this.regId = regId;
        this.regDate = regDate;
        this.vatRate = vatRate;
        this.todayDate = todayDate;
        this.useSystemDate = useSystemDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.updateTime = updateTime;
        this.version = version;
    }
    
    
}
