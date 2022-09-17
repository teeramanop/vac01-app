package com.repository;

import com.model.CompanyModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCompanyRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public int add(CompanyModel company) {
        return jdbcTemplate.update(
                "insert into company "
                        + "(CompCode"
                        + ", NameTh"
                        + ", NameEn"
                        + ", Addr1"
                        + ", Addr2"
                        + ", Addr3"
                        + ", Zip"
                        + ", Tel"
                        + ", Fax"
                        + ", Email"
                        + ", WebSite"
                        + ", RegId"
                        + ", RegDate"
                        + ", VatRate"
                        + ", TodayDate"
                        + ", UseSystemDate"
                        + ", UpdateBy"
                        + ", UpdateDate"
                        + ", UpdateTime"
                        + ", Version)"
                        + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                company.getCompCode(),
                company.getNameTh(),
                company.getNameEn(),
                company.getAddr1(),
                company.getAddr2(),
                company.getAddr3(),
                company.getZip(),
                company.getTel(),
                company.getFax(),
                company.getEmail(),
                company.getWebSite(),
                company.getRegId(),
                company.getRegDate(),
                company.getVatRate(),
                company.getTodayDate(),
                company.getUseSystemDate(),
                company.getUpdateBy(),
                company.getUpdateDate(),
                company.getUpdateTime(),
                company.getVersion());
    }
 
    public int update(CompanyModel company) {
        return jdbcTemplate.update(
                "update company set "
                        + "NameTh = ? "
                        + ", NameEn = ? "
                        + ", Addr1 = ? "
                        + ", Addr2 = ? "
                        + ", Addr3 = ? "
                        + ", Zip = ? "
                        + ", Tel = ? "
                        + ", Fax = ? "
                        + ", Email = ? "
                        + ", WebSite = ? "
                        + ", RegId = ? "
                        + ", RegDate = ? "
                        + ", VatRate = ? "
                        + ", TodayDate = ? "
                        + ", UseSystemDate = ? "
                        + ", UpdateBy = ? "
                        + ", UpdateDate = ? "
                        + ", UpdateTime = ? "
                        + ", Version = ? "
                        + " where CompCode = ?",
                
                company.getNameTh(),
                company.getNameEn(),
                company.getAddr1(),
                company.getAddr2(),
                company.getAddr3(),
                company.getZip(),
                company.getTel(),
                company.getFax(),
                company.getEmail(),
                company.getWebSite(),
                company.getRegId(),
                company.getRegDate(),
                company.getVatRate(),
                company.getTodayDate(),
                company.getUseSystemDate(),
                company.getUpdateBy(),
                company.getUpdateDate(),
                company.getUpdateTime(),
        
                company.getCompCode());
    }
    
    public int delete(String compCode) {
        return jdbcTemplate.update(
                "delete from company where CompCode = ?",
                compCode);
    }

    // jdbcTemplate.queryForObject, populates a single object
    public CompanyModel findByPK(String compCode) {
        try {
        return jdbcTemplate.queryForObject(
                "select * from company where CompCode = ?",
                new Object[]{compCode},
                (rs, rowNum) ->
                        new CompanyModel(
                                rs.getString("CompCode"),
                                rs.getString("NameTh"),
                                rs.getString("NameEn"),
                                rs.getString("Addr1"),
                                rs.getString("Addr2"),
                                rs.getString("Addr3"),
                                rs.getString("Zip"),
                                rs.getString("Tel"),
                                rs.getString("Fax"),
                                rs.getString("Email"),
                                rs.getString("WebSite"),
                                rs.getString("RegId"),
                                rs.getString("RegDate"),
                                rs.getDouble("VatRate"),
                                rs.getString("TodayDate"),
                                rs.getString("UseSystemDate"),
                                rs.getString("UpdateBy"),
                                rs.getString("UpdateDate"),
                                rs.getString("UpdateTime"),
                                rs.getInt("Version")
                        )
        );
        
        } catch (Exception ex) {
            return null;        
        }   
    }
    
    public List<CompanyModel> findAll() {
        return jdbcTemplate.query(
                "select * from company",
                (rs, rowNum) ->
                        new CompanyModel(
                                rs.getString("CompCode"),
                                rs.getString("NameTh"),
                                rs.getString("NameEn"),
                                rs.getString("Addr1"),
                                rs.getString("Addr2"),
                                rs.getString("Addr3"),
                                rs.getString("Zip"),
                                rs.getString("Tel"),
                                rs.getString("Fax"),
                                rs.getString("Email"),
                                rs.getString("WebSite"),
                                rs.getString("RegId"),
                                rs.getString("RegDate"),
                                rs.getDouble("VatRate"),
                                rs.getString("TodayDate"),
                                rs.getString("UseSystemDate"),
                                rs.getString("UpdateBy"),
                                rs.getString("UpdateDate"),
                                rs.getString("UpdateTime"),
                                rs.getInt("Version")
                        )
        );
    }
    
    public List<CompanyModel> findByCompCode(String compCode) {
        return jdbcTemplate.query(
                "select * from company where CompCode like ?",
                new Object[]{compCode + "%"},
                (rs, rowNum) ->
                        new CompanyModel(
                                rs.getString("CompCode"),
                                rs.getString("NameTh"),
                                rs.getString("NameEn"),
                                rs.getString("Addr1"),
                                rs.getString("Addr2"),
                                rs.getString("Addr3"),
                                rs.getString("Zip"),
                                rs.getString("Tel"),
                                rs.getString("Fax"),
                                rs.getString("Email"),
                                rs.getString("WebSite"),
                                rs.getString("RegId"),
                                rs.getString("RegDate"),
                                rs.getDouble("VatRate"),
                                rs.getString("TodayDate"),
                                rs.getString("UseSystemDate"),
                                rs.getString("UpdateBy"),
                                rs.getString("UpdateDate"),
                                rs.getString("UpdateTime"),
                                rs.getInt("Version")
                        )
        );
    }
    public List<CompanyModel> findByCompName(String compName) {
        return jdbcTemplate.query(
                "select * from company where NameTh like ? or NameEn like ?",
                new Object[]{"%" + compName + "%","%" + compName + "%"},
                (rs, rowNum) ->
                        new CompanyModel(
                                rs.getString("CompCode"),
                                rs.getString("NameTh"),
                                rs.getString("NameEn"),
                                rs.getString("Addr1"),
                                rs.getString("Addr2"),
                                rs.getString("Addr3"),
                                rs.getString("Zip"),
                                rs.getString("Tel"),
                                rs.getString("Fax"),
                                rs.getString("Email"),
                                rs.getString("WebSite"),
                                rs.getString("RegId"),
                                rs.getString("RegDate"),
                                rs.getDouble("VatRate"),
                                rs.getString("TodayDate"),
                                rs.getString("UseSystemDate"),
                                rs.getString("UpdateBy"),
                                rs.getString("UpdateDate"),
                                rs.getString("UpdateTime"),
                                rs.getInt("Version")
                        )
        );
    }
    
    
}
