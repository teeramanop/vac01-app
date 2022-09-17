package com.repository;

import com.model.BranchModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcBranchRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(BranchModel branch) {
        return jdbcTemplate.update(
                "insert into branch "
                        + "(CompCode"
                        + ", BranchCode"
                        + ", NameTh"
                        + ", NameEn"
                        + ", Addr1"
                        + ", Addr2"
                        + ", Addr3"
                        + ", Zip"
                        + ", Tel"
                        + ", Fax"
                        + ", WebSite"
                        + ", RegId"
                        + ", RegDate"
                        + ", VatRate"
                        + ", CreateBy"
                        + ", CreateDate"
                        + ", CreateTime"
                        + ", UpdateBy"
                        + ", UpdateDate"
                        + ", UpdateTime"
                        + ", Version)"
                        + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                branch.getCompCode(),
                branch.getBranchCode(),
                branch.getNameTh(),
                branch.getNameEn(),
                branch.getAddr1(),
                branch.getAddr2(),
                branch.getAddr3(),
                branch.getZip(),
                branch.getTel(),
                branch.getFax(),
                branch.getWebSite(),
                branch.getRegId(),
                branch.getRegDate(),
                branch.getVatRate(),
                branch.getCreateBy(),
                branch.getCreateDate(),
                branch.getCreateTime(),
                branch.getUpdateBy(),
                branch.getUpdateDate(),
                branch.getUpdateTime(),
                branch.getVersion());
    }
 
    public int update(BranchModel branch) {
        return jdbcTemplate.update(
                "update branch set "
                        + "NameTh = ? "
                        + ", NameEn = ? "
                        + ", Addr1 = ? "
                        + ", Addr2 = ? "
                        + ", Addr3 = ? "
                        + ", Zip = ? "
                        + ", Tel = ? "
                        + ", Fax = ? "
                        + ", WebSite = ? "
                        + ", RegId = ? "
                        + ", RegDate = ? "
                        + ", VatRate = ? "
                        + ", CreateBy = ? "
                        + ", CreateDate = ? "
                        + ", CreateTime = ? "
                        + ", UpdateBy = ? "
                        + ", UpdateDate = ? "
                        + ", UpdateTime = ? "
                        + ", Version = ? "
                        + " where CompCode = ? and BranchCode = ?",
                
                branch.getNameTh(),
                branch.getNameEn(),
                branch.getAddr1(),
                branch.getAddr2(),
                branch.getAddr3(),
                branch.getZip(),
                branch.getTel(),
                branch.getFax(),
                branch.getWebSite(),
                branch.getRegId(),
                branch.getRegDate(),
                branch.getVatRate(),
                branch.getCreateBy(),
                branch.getCreateDate(),
                branch.getCreateTime(),
                branch.getUpdateBy(),
                branch.getUpdateDate(),
                branch.getUpdateTime(),
        
                branch.getCompCode(),
                branch.getBranchCode());
    }
    
    public int delete(String compCode, String branchCode) {
        return jdbcTemplate.update(
                "delete from branch where CompCode = ? and BranchCode = ?",
                compCode,branchCode);
    }

    // jdbcTemplate.queryForObject, populates a single object
    public BranchModel findByPK(String compCode,String branchCode) {
        try {
        return jdbcTemplate.queryForObject(
                "select * from branch where CompCode = ? and BranchCode = ?",
                new Object[]{compCode,branchCode},
                (rs, rowNum) ->
                        new BranchModel(
                                rs.getString("CompCode"),
                                rs.getString("BranchCode"),
                                rs.getString("NameTh"),
                                rs.getString("NameEn"),
                                rs.getString("Addr1"),
                                rs.getString("Addr2"),
                                rs.getString("Addr3"),
                                rs.getString("Zip"),
                                rs.getString("Tel"),
                                rs.getString("Fax"),
                                rs.getString("WebSite"),
                                rs.getString("RegId"),
                                rs.getString("RegDate"),
                                rs.getDouble("VatRate"),
                                rs.getString("CreateBy"),
                                rs.getString("CreateDate"),
                                rs.getString("CreateTime"),
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
    
    public List<BranchModel> findByCompCode(String compCode) {
        return jdbcTemplate.query(
                "select * from branch where CompCode = ?",
                new Object[]{compCode},
                (rs, rowNum) ->
                        new BranchModel(
                                rs.getString("CompCode"),
                                rs.getString("BranchCode"),
                                rs.getString("NameTh"),
                                rs.getString("NameEn"),
                                rs.getString("Addr1"),
                                rs.getString("Addr2"),
                                rs.getString("Addr3"),
                                rs.getString("Zip"),
                                rs.getString("Tel"),
                                rs.getString("Fax"),
                                rs.getString("WebSite"),
                                rs.getString("RegId"),
                                rs.getString("RegDate"),
                                rs.getDouble("VatRate"),
                                rs.getString("CreateBy"),
                                rs.getString("CreateDate"),
                                rs.getString("CreateTime"),
                                rs.getString("UpdateBy"),
                                rs.getString("UpdateDate"),
                                rs.getString("UpdateTime"),
                                rs.getInt("Version")
                        )
        );
    }
    
    
}
