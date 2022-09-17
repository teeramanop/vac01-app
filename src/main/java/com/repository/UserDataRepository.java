package com.repository;

import com.entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, String> {
    @Query("select c from UserData c" +
                " WHERE c.userId = ?1")
    UserData select(String userId);
    
    @Query("select c from UserData c" +
                " ORDER BY c.userId")
    UserData[] SelectAll();
    
    @Query("select c from UserData c" +
                " Where c.userId like ?1%")
    UserData[] SelectByUserId(String userId);
    
    @Query("select c from UserData c" +
                " Where c.userFname like %?1%")
    UserData[] SelectByUserName(String userName);

}
