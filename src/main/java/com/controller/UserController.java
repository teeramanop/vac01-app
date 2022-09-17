package com.controller;

import com.model.MenuItem;
import com.model.RoleModel;
import com.model.UserModel;
import com.model.UserRightModel;
import com.entities.UserData;
import com.entities.Userrole;
import com.entities.UserrolePK;
import com.exception.ApplException;
import com.util.Util;
import engine.Activities;
import engine.Participants;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xpdlparser.Pack;
import xpdlparser.XPDLParser;
import com.repository.UserDataRepository;
import com.repository.UserroleRepository;
import org.springframework.transaction.annotation.Transactional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private PasswordEncoder bcryptEncoder;  
    @Autowired
    private UserDataRepository userDataRepo;
    @Autowired
    private UserroleRepository userroleRepo;
    
    @PostMapping("/add")
    @ResponseBody   
    public ResponseEntity<UserModel> add(HttpServletRequest request,@RequestBody UserModel user) {
        try {
            //UserDataCtrl cUserData = new UserDataCtrl();
            UserData aUserData = new UserData();
            aUserData.setUserId(user.getUserId());
            aUserData.setUserFname(user.getUserFname());
            aUserData.setUserLname(user.getUserLname());
            aUserData.setPassword(bcryptEncoder.encode(user.getPassword()));
            aUserData.setEmail(user.getEmail());
            aUserData.setPhone1(user.getPhone1());
            aUserData.setPhone2(user.getPhone2());
            aUserData.setCompCode("VAC");
            aUserData.setBranchCode("000");
            aUserData.setVersion(0);
            
//            if (!cUserData.add(aUserData)) {
//                // Error
//                user.setRespMsg("Error add");
//                return new ResponseEntity<>(user,HttpStatus.OK);        
//            }
            this.userDataRepo.save(aUserData);
            user.setRespMsg("Completed");

            return new ResponseEntity<>(user,HttpStatus.OK);        
            
        } catch (Exception ex) {
            return new ResponseEntity<>(user,HttpStatus.OK);        
            
        }
    }
    
    @PostMapping("/update")
    @ResponseBody   
    public ResponseEntity<UserModel> update(HttpServletRequest request,@RequestBody UserModel user) {
        try {
            UserModel aUserModel = new UserModel();

            //UserDataCtrl cUserData = new UserDataCtrl();
            //UserData aUserData = cUserData.Select(user.getUserId());
            UserData aUserData = this.userDataRepo.select(user.getUserId());
            if (aUserData==null) {
                //Not found
                aUserModel.setRespMsg("Error: Not found");
                return new ResponseEntity<>(aUserModel,HttpStatus.OK);        
            }
            aUserData.setUserFname(user.getUserFname());
            aUserData.setUserLname(user.getUserLname());
            if (!user.getPassword().equals("")) {
                aUserData.setPassword(bcryptEncoder.encode(user.getPassword()));
            }
            aUserData.setEmail(user.getEmail());
            aUserData.setPhone1(user.getPhone1());
            aUserData.setPhone2(user.getPhone2());
//            aUserData.setCompCode(user.getCompCode());
//            aUserData.setBranchCode(user.getBranchCode());
            
//            if (!cUserData.update(aUserData)) {
//                // Error
//                aUserModel.setRespMsg("Error: Update");
//                return new ResponseEntity<>(aUserModel,HttpStatus.OK);        
//            }
            this.userDataRepo.save(aUserData);
            aUserModel.setUserId(aUserData.getUserId());
            aUserModel.setUserFname(aUserData.getUserFname());
            aUserModel.setUserLname(aUserData.getUserLname());
            aUserModel.setPassword("");
            aUserModel.setEmail(aUserData.getEmail());
            aUserModel.setPhone1(aUserData.getPhone1());
            aUserModel.setPhone2(aUserData.getPhone2());
            aUserModel.setCompCode(aUserData.getCompCode());
            aUserModel.setBranchCode(aUserData.getCompCode());
            aUserModel.setVersion(aUserData.getVersion());
            aUserModel.setRespMsg("Completed");
            
            return new ResponseEntity<>(aUserModel ,HttpStatus.OK);        
        } catch (Exception ex) {
            return new ResponseEntity<>(null ,HttpStatus.OK);        
        }
        
    }
    
    @PostMapping("/delete")
    @ResponseBody   
    public ResponseEntity<UserModel> delete(HttpServletRequest request,@RequestBody UserModel user) {
        try {
            //UserDataCtrl cUserData = new UserDataCtrl();
            //UserData aUserData = cUserData.Select(user.getUserId());
            UserData aUserData = this.userDataRepo.select(user.getUserId());
            if (aUserData==null) {
                //Not found
                user.setRespMsg("Not found");
                return new ResponseEntity<>(user,HttpStatus.OK);        
            }
//            if (!cUserData.remove(aUserData)) {
//                // Error
//                user.setRespMsg("Error delete");
//                return new ResponseEntity<>(user,HttpStatus.OK);        
//            }
            this.userDataRepo.delete(aUserData);
            this.userroleRepo.removeByUserId(aUserData.getUserId());
            user.setRespMsg("Completed");
            return new ResponseEntity<>(user,HttpStatus.OK);        

        } catch (Exception ex) {
            return null;
        }
        
    }

    @PostMapping(value = "/select")
    @ResponseBody
    public ResponseEntity<UserModel> getRecord(HttpServletRequest request,@RequestBody String userid) {
        try {
            //UserDataCtrl cUserData = new UserDataCtrl();
            //UserData aUserData = cUserData.Select(userid);
            UserData aUserData = this.userDataRepo.select(userid);
            UserModel aUserModel = new UserModel();
            if (aUserData==null) {
                // Error
                aUserModel.setRespMsg("Error Not found");
                return new ResponseEntity<>(aUserModel,HttpStatus.OK);        
            }
            aUserModel.setUserId(aUserData.getUserId());
            aUserModel.setUserFname(aUserData.getUserFname());
            aUserModel.setUserLname(aUserData.getUserLname());
            aUserModel.setPassword("");
            aUserModel.setEmail(aUserData.getEmail());
            aUserModel.setPhone1(aUserData.getPhone1());
            aUserModel.setPhone2(aUserData.getPhone2());
            aUserModel.setCompCode(aUserData.getCompCode());
            aUserModel.setBranchCode(aUserData.getBranchCode());
            aUserModel.setVersion(aUserData.getVersion());
            aUserModel.setRespMsg("Completed");

            return new ResponseEntity<>(aUserModel,HttpStatus.OK);        
        }
        catch (Exception ex) {
            return null;
        }
        
    }
    @PostMapping(value = "/selectbyuserid")
    @ResponseBody
    @CrossOrigin( origins = "*" )
    public ResponseEntity<ArrayList> selectbyid(HttpServletRequest request,@RequestBody String userid) {
        try {
            //UserDataCtrl cUserData = new UserDataCtrl();
            //UserData[] aUserDatas = cUserData.SelectByUserId(userid);
            UserData[] aUserDatas = this.userDataRepo.SelectByUserId(userid);
            ArrayList<UserModel> aUserModels = new ArrayList<>();
            
            int ii=0;
            while (ii<aUserDatas.length) {
                UserModel aUserModel = new UserModel();
                aUserModel.setUserId(aUserDatas[ii].getUserId());
                aUserModel.setUserFname(aUserDatas[ii].getUserFname());
                aUserModel.setUserLname(aUserDatas[ii].getUserLname());
                aUserModel.setEmail(aUserDatas[ii].getEmail());
                aUserModel.setPhone1(aUserDatas[ii].getPhone1());
                aUserModel.setPhone2(aUserDatas[ii].getPhone2());
                aUserModel.setCompCode(aUserDatas[ii].getCompCode());
                aUserModel.setBranchCode(aUserDatas[ii].getBranchCode());
                aUserModel.setVersion(aUserDatas[ii].getVersion());
                aUserModel.setRespMsg("Completed");
                
                aUserModels.add(aUserModel);
                ii++;
            }
            
            return new ResponseEntity<>(aUserModels,HttpStatus.OK);        
        }
        catch (Exception ex) {
            return null;
        }
    
    }
    @PostMapping(value = "/selectbyusername")
    @ResponseBody
    @CrossOrigin( origins = "*" )
    public ResponseEntity<ArrayList> selectbyname(HttpServletRequest request,@RequestBody String username) {
        try {
            //UserDataCtrl cUserData = new UserDataCtrl();
            //UserData[] aUserDatas = cUserData.SelectByUserId(userid);
            UserData[] aUserDatas = this.userDataRepo.SelectByUserName(username);
            ArrayList<UserModel> aUserModels = new ArrayList<>();
            
            int ii=0;
            while (ii<aUserDatas.length) {
                UserModel aUserModel = new UserModel();
                aUserModel.setUserId(aUserDatas[ii].getUserId());
                aUserModel.setUserFname(aUserDatas[ii].getUserFname());
                aUserModel.setUserLname(aUserDatas[ii].getUserLname());
                aUserModel.setEmail(aUserDatas[ii].getEmail());
                aUserModel.setPhone1(aUserDatas[ii].getPhone1());
                aUserModel.setPhone2(aUserDatas[ii].getPhone2());
                aUserModel.setCompCode(aUserDatas[ii].getCompCode());
                aUserModel.setBranchCode(aUserDatas[ii].getBranchCode());
                aUserModel.setVersion(aUserDatas[ii].getVersion());
                aUserModel.setRespMsg("Completed");
                
                aUserModels.add(aUserModel);
                ii++;
            }
            
            return new ResponseEntity<>(aUserModels,HttpStatus.OK);        
        }
        catch (Exception ex) {
            return null;
        }
    
    }
    
    @GetMapping(value = "/searchbyid")
    @ResponseBody
    public ResponseEntity<UserModel[]> SearchById(HttpServletRequest request,@RequestParam String userid) {
        try {
//            UserModel aUsersession =(UserModel) request.getSession().getAttribute("userinfo");
//            if (aUsersession==null) {
//                // Not Signon
//            }
            
            //UserDataCtrl cUserData = new UserDataCtrl();
            //UserData[] aUserDatas = cUserData.SelectByUserId(userid);
            UserData[] aUserDatas = this.userDataRepo.SelectByUserId(userid);

            UserModel[] aUserModels = new UserModel[aUserDatas.length];

            int ii=0;
            while (ii<aUserDatas.length) {
                UserModel aUserModel = new UserModel();
                aUserModel.setUserId(aUserDatas[ii].getUserId());
                aUserModel.setUserFname(aUserDatas[ii].getUserFname());
                aUserModel.setUserLname(aUserDatas[ii].getUserLname());
                aUserModel.setPassword(aUserDatas[ii].getPassword());
                aUserModel.setEmail(aUserDatas[ii].getEmail());
                aUserModel.setPhone1(aUserDatas[ii].getPhone1());
                aUserModel.setPhone2(aUserDatas[ii].getPhone2());
                aUserModel.setCompCode(aUserDatas[ii].getCompCode());
                aUserModel.setBranchCode(aUserDatas[ii].getBranchCode());
                aUserModel.setVersion(aUserDatas[ii].getVersion());

                aUserModels[ii] = aUserModel;
                ii++;
            }
            
            return new ResponseEntity<>(aUserModels,HttpStatus.OK);        
        }
        catch (Exception ex) {
            return null;
        }
        
    }

    @PostMapping(value = "/userroles")
    @ResponseBody
    public ResponseEntity<RoleModel[]> UserRoles(HttpServletRequest request,@RequestBody String userid) {
        try {
//            UserroleCtrl cUserRole = new UserroleCtrl();
//            Userrole[] aUserRoles = cUserRole.SelectByUserId(userid);
            Userrole[] aUserRoles = this.userroleRepo.selectByUserId(userid);
            RoleModel[] aRoleModels = new RoleModel[aUserRoles.length];

            int ii=0;
            while (ii<aUserRoles.length) {
                RoleModel aRoleModel = new RoleModel();
                aRoleModel.setRoleId(aUserRoles[ii].getUserrolePK().getRole());
                aRoleModel.setRoleDesc(aUserRoles[ii].getUserrolePK().getRole());
                
                aRoleModels[ii] = aRoleModel;
                ii++;
            }
            
            return new ResponseEntity<>(aRoleModels,HttpStatus.OK);        
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    @Transactional(rollbackFor = ApplException.class)
    @PostMapping(value = "/updateroles")
    @ResponseBody
    public ResponseEntity<RoleModel[]> UpdateRoles(HttpServletRequest request,@RequestParam String userid,@RequestBody RoleModel[] roles) {
        try {
//            UserroleCtrl cUserRole = new UserroleCtrl();
//            if (!cUserRole.removeByUserId(userid)) {
//                return new ResponseEntity<>(roles,HttpStatus.EXPECTATION_FAILED);                
//            }
            this.userroleRepo.removeByUserId(userid);
            int ii=0;
            while (ii<roles.length) {
                if (roles[ii].isCheck()) {
                    Userrole aUserRole = new Userrole();
                    UserrolePK kUserRole = new UserrolePK();
                    kUserRole.setUserId(userid);
                    kUserRole.setRole(roles[ii].getRoleId());
                    aUserRole.setUserrolePK(kUserRole);
//                    if (!cUserRole.add(aUserRole)) {
//                        return new ResponseEntity<>(roles,HttpStatus.EXPECTATION_FAILED);                
//                    }
                    this.userroleRepo.save(aUserRole);
                }
                roles[ii].setRespMsg("Completed");
                ii++;
            }
            
            return new ResponseEntity<>(roles,HttpStatus.OK);        
        }
        catch (Exception ex) {
            return null;
        }
    }

    @PostMapping(value = "/availableroles")
    @ResponseBody
    public ResponseEntity<ArrayList> AvailableRoles(HttpServletRequest request,@RequestBody String userid) {
        try {
//            UserroleCtrl cUserRole = new UserroleCtrl();
//            Userrole[] aUserRoles = cUserRole.SelectByUserId(userid);
            Userrole[] aUserRoles = this.userroleRepo.selectByUserId(userid);
            XPDLParser xp = new XPDLParser();
            Pack Xpack = new xpdlparser.Pack();

            String fileName = "src/main/resources/data/process.xpdl";      
            //InputStream stream = FileUtils.openInputStream(initialFile);     
            InputStream stream = new FileInputStream(fileName);
            Xpack = (xpdlparser.Pack)xp.StartParse("KBANK"+"Kasikornbank Public Company Limited","B0CD7896D0486286370F9AAAF97C5091BF8A278512A527479ABA5AA1C1831EE524A019507F9255B0995AC19B8F251B6C0A0F",stream);
            Participants aParticipants = new Participants();
            aParticipants.selectParticipants(Xpack);
            
            ArrayList<RoleModel> aRoleModels = new ArrayList<>();
            ArrayList roles = new ArrayList();
            roles.clear();
            int ii = 0;
            while (ii < aUserRoles.length) {
                roles.add(aUserRoles[ii].getUserrolePK().getRole());
                ii++;
            }
            ii = 0;
            while (ii<aParticipants.getReccount()) {
                int jj = 0;
                boolean eq = false;
                while (jj < roles.size()){
                    if (roles.get(jj).equals(aParticipants.getId(ii))) { 
                        eq = true;
                    }
                    jj++;
                }
                if (!eq) { 
                    RoleModel aRoleModel = new RoleModel();
                    aRoleModel.setRoleId(aParticipants.getId(ii));
                    aRoleModel.setRoleDesc(aParticipants.getName(ii));

                    aRoleModels.add(aRoleModel);
                }
                ii++;
            }
            
            return new ResponseEntity<>(aRoleModels,HttpStatus.OK);        
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    @GetMapping(value = "/allroles")
    @ResponseBody
    public ResponseEntity<ArrayList> AllRoles(HttpServletRequest request) {
        try {
            XPDLParser xp = new XPDLParser();
            Pack Xpack = new xpdlparser.Pack();

            String fileName = "src/main/resources/data/process.xpdl";      
            //InputStream stream = FileUtils.openInputStream(initialFile);     
            InputStream stream = new FileInputStream(fileName);
            Xpack = (xpdlparser.Pack)xp.StartParse("KBANK"+"Kasikornbank Public Company Limited","B0CD7896D0486286370F9AAAF97C5091BF8A278512A527479ABA5AA1C1831EE524A019507F9255B0995AC19B8F251B6C0A0F",stream);
            Participants aParticipants = new Participants();
            aParticipants.selectParticipants(Xpack);
            
            ArrayList<RoleModel> aRoleModels = new ArrayList<>();
//            ArrayList roles = new ArrayList();
//            roles.clear();
//            int ii = 0;
//            while (ii < aUserRoles.length) {
//                roles.add(aUserRoles[ii].getUserRolePK().getRole());
//                ii++;
//            }
            int ii = 0;
            while (ii<aParticipants.getReccount()) {
                RoleModel aRoleModel = new RoleModel();
                aRoleModel.setRoleId(aParticipants.getId(ii));
                aRoleModel.setRoleDesc(aParticipants.getName(ii));
                aRoleModels.add(aRoleModel);
                ii++;
            }
            
            return new ResponseEntity<>(aRoleModels,HttpStatus.OK);        
        }
        catch (Exception ex) {
            return null;
        }
    }

    @GetMapping(value = "/userright")
    @ResponseBody
    public ResponseEntity<UserRightModel[]> getUserRights(HttpServletRequest request,@RequestParam String userid,@RequestParam String password) {
        try {
//            UserDataCtrl cUserData = new UserDataCtrl();
//            UserroleCtrl cUserRole = new UserroleCtrl();
            
            String encPassword = Util.Encrypt(password);
//            UserData aUserData = cUserData.Select(userid);
            UserData aUserData = this.userDataRepo.select(userid);
            if (aUserData!=null) {
                if (password.equals(aUserData.getPassword())
                    || encPassword.equals(aUserData.getPassword())) {
                    ArrayList roles = new ArrayList();
                    int roleCount; 	
                        
//                    Userrole[] aUserRoles = cUserRole.SelectByUserId(userid);
                    Userrole[] aUserRoles = this.userroleRepo.selectByUserId(userid);
                    roleCount = (aUserRoles.length);
                    roles.clear();
                    int ii = 0;
                    while (ii<roleCount) {
                        roles.add(aUserRoles[ii].getUserrolePK().getRole());
                        ii++;
                    }
                    XPDLParser xp = new XPDLParser();
                    Pack Xpack = new xpdlparser.Pack();
//                    String fileName = "data/process.xpdl";      
//                    ClassLoader classLoader = new Sb01Application().getClass().getClassLoader();
// 
//                    File initialFile = new File(classLoader.getResource(fileName).getFile());

                    String fileName = "src/main/resources/data/process.xpdl";      

                    //InputStream stream = FileUtils.openInputStream(initialFile);     
                    InputStream stream = new FileInputStream(fileName);
                    Xpack = (xpdlparser.Pack)xp.StartParse("KBANK"+"Kasikornbank Public Company Limited","B0CD7896D0486286370F9AAAF97C5091BF8A278512A527479ABA5AA1C1831EE524A019507F9255B0995AC19B8F251B6C0A0F",stream);
                    Activities aActivities = new Activities();
                    aActivities.SelectActByRoles(Xpack,roles,roleCount);
                    
                    UserRightModel[] aUserRightModels = new UserRightModel[aActivities.getReccount()];
                    ii = 0;
                    while (ii<aActivities.getReccount()) {
                        UserRightModel aUserRightModel = new UserRightModel();
                        aUserRightModel.setUserId(aUserData.getUserId());
                        aUserRightModel.setUserName(aUserData.getUserFname().trim() + " " +aUserData.getUserLname().trim());
                        aUserRightModel.setRole("");
                        aUserRightModel.setProgName(aActivities.getToolId()[ii]);
                        aUserRightModel.setProgDesc(aActivities.getName()[ii]);
                        
                        aUserRightModels[ii] = aUserRightModel;

                        ii++;
                    }
                    return new ResponseEntity<>(aUserRightModels,HttpStatus.OK);        
                }
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    @GetMapping(value = "/usermenu")
    @ResponseBody
    public ResponseEntity<MenuItem[]> getUserMenu(HttpServletRequest request,@RequestParam String userid) {
        try {
//            UserDataCtrl cUserData = new UserDataCtrl();
//            UserroleCtrl cUserRole = new UserroleCtrl();
            
            //String encPassword = Util.Encrypt(password);
            //UserData aUserData = cUserData.Select(userid);
            UserData aUserData = this.userDataRepo.select(userid);
            if (aUserData!=null) {
//                if (password.equals(aUserData.getPassword())
//                    || encPassword.equals(aUserData.getPassword())) {
//                    // save session
//                    UserModel aUserSession =(UserModel) request.getSession().getAttribute("userinfo");
//                    if (aUserSession==null) {
//                        aUserSession = new UserModel();
//                        aUserSession.setUserId(aUserData.getUserId());
//                        aUserSession.setUserName(aUserData.getUserName());
//                        request.getSession().setAttribute("userinfo",aUserSession);
//                    }
//                    //
                    ArrayList roles = new ArrayList();
                    int roleCount; 	
                        
//                    Userrole[] aUserRoles = cUserRole.SelectByUserId(aUserData.getUserId());
                    Userrole[] aUserRoles = this.userroleRepo.selectByUserId(userid);
                    roleCount = (aUserRoles.length);
                    roles.clear();
                    int ii = 0;
                    while (ii<roleCount) {
                        roles.add(aUserRoles[ii].getUserrolePK().getRole());
                        ii++;
                    }
                    XPDLParser xp = new XPDLParser();
                    Pack Xpack = new xpdlparser.Pack();
                    String fileName = "src/main/resources/data/process.xpdl";      

                    InputStream stream = new FileInputStream(fileName);
                    Xpack = (xpdlparser.Pack)xp.StartParse("KBANK"+"Kasikornbank Public Company Limited","B0CD7896D0486286370F9AAAF97C5091BF8A278512A527479ABA5AA1C1831EE524A019507F9255B0995AC19B8F251B6C0A0F",stream);
                    Activities aActivities = new Activities();
                    aActivities.SelectActByRoles(Xpack,roles,roleCount);
                    
                    MenuItem[] aMenuItems = new MenuItem[aActivities.getReccount()];
                    ii = 0;
                    while (ii<aActivities.getReccount()) {
                        MenuItem aMenuItem = new MenuItem();
                        aMenuItem.setUserId(aUserData.getUserId());
                        aMenuItem.setUserName(aUserData.getUserFname());
                        aMenuItem.setWorkflowProcessId(aActivities.getWorkflowProcessId()[ii]);
                        aMenuItem.setWorkflowProcessName(aActivities.getWorkflowProcessName()[ii]);
                        aMenuItem.setProgId(aActivities.getId()[ii]);
                        aMenuItem.setProgName(aActivities.getName()[ii]);
                        aMenuItem.setProgDesc(aActivities.getDesc()[ii]);
                        aMenuItem.setPerformer(aActivities.getPerformer()[ii]);
                        aMenuItem.setToolId(aActivities.getToolId()[ii]);
                        aMenuItem.setIcon("ion-ios-arrow-forward");
                        
                        aMenuItems[ii] = aMenuItem;

                        ii++;
                    }
//                    ObjectMapper mapper = new ObjectMapper();
//                    String jsonString = mapper.writeValueAsString(aMenuItems);
//                    HttpHeaders headers = new HttpHeaders();
//                    headers.setOrigin("Access-Control-Allow-Origin");
                    return new ResponseEntity<>(aMenuItems,HttpStatus.OK);        
//                }
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    @GetMapping(value = "/report")
    public @ResponseBody byte[] export(HttpServletResponse response,@RequestParam String userid) throws IOException, JRException {
        JasperPrint jasperPrint = null;

        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"users.pdf\""));

        List aUserList = getUserList();
                
        //OutputStream out = response.getOutputStream();
        jasperPrint = createPdfReport(aUserList);
        //JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
        return bytes;
    }    
    // Method to create the pdf file using the employee list datasource.
    private JasperPrint createPdfReport(final List<UserData> users) throws JRException {
        // Fetching the .jrxml file from the resources folder.
        final InputStream stream = this.getClass().getResourceAsStream("/RptUser01.jrxml");
 
        // Compile the Jasper report from .jrxml to .japser
        final JasperReport report = JasperCompileManager.compileReport(stream);
 
        // Fetching the employees from the data source.
        final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(users);
 
        // Adding the additional parameters to the pdf.
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "VAC");
 
        // Filling the report with the employee data and additional parameters information.
        final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);
        return print;

// 
//        // Users can change as per their project requrirements or can take it as request input requirement.
//        // For simplicity, this tutorial will automatically place the file under the "c:" drive.
//        // If users want to download the pdf file on the browser, then they need to use the "Content-Disposition" technique.
//        final String filePath = "\\";
//        // Export the report to a PDF file.
//        JasperExportManager.exportReportToPdfFile(print, filePath + "Employee_report.pdf");

    }    
    private ArrayList<UserData> getUserList() {
        ArrayList<UserData> aUserList = new ArrayList<UserData>();
//        UserDataCtrl cUserData = new UserDataCtrl();
//        UserData[] aUserDatas = cUserData.SelectAll();
          UserData[] aUserDatas = this.userDataRepo.SelectAll();
        //UserData aUserData = new UserData();
        int ii = 0;
        while (ii<aUserDatas.length) {
//            aUserData = new UserData();
//            aUserData.setUserId(aUserDatas[ii].getUserId());
//            aUserData.setUserName(aUserDatas[ii].getUserName());

            aUserList.add(aUserDatas[ii]);
            ii++;
        }
        return aUserList;
    }
    
}

