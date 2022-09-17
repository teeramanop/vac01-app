package com.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchModel implements Serializable {
    private String compCode;
    private String branchCode;
    private String nameTh;
    private String nameEn;
    private String addr1;
    private String addr2;
    private String addr3;
    private String zip;
    private String tel;
    private String fax;
    private String webSite;
    private String regId;  
    private String regDate;
    private Double vatRate;
    private String createBy;
    private String createDate;
    private String createTime;
    private String updateBy;
    private String updateDate;
    private String updateTime;
    int version;
    
    // API response message
    private String respMsg;

    public BranchModel() {
    }

    public BranchModel(String compCode, String branchCode, String nameTh, String nameEn, String addr1, String addr2, String addr3, String zip, String tel, String fax, String webSite, String regId, String regDate, Double vatRate, String createBy, String createDate, String createTime, String updateBy, String updateDate, String updateTime, int version) {
        this.compCode = compCode;
        this.branchCode = branchCode;
        this.nameTh = nameTh;
        this.nameEn = nameEn;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.addr3 = addr3;
        this.zip = zip;
        this.tel = tel;
        this.fax = fax;
        this.webSite = webSite;
        this.regId = regId;
        this.regDate = regDate;
        this.vatRate = vatRate;
        this.createBy = createBy;
        this.createDate = createDate;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.updateTime = updateTime;
        this.version = version;
    }
    
    

}
