package com.repository;

import com.entities.Userrole;
import com.entities.UserrolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserroleRepository extends JpaRepository<Userrole, UserrolePK> {
        Userrole findByUserrolePK(UserrolePK userrolePK);
        
        @Modifying
        @Transactional
        @Query("delete from Userrole c" +
                " WHERE c.userrolePK.userId = ?1")
        void removeByUserId(String userId);
    
        @Query("select c from Userrole c" +
                " WHERE c.userrolePK.userId = ?1")
        Userrole[] selectByUserId(String userId);
        
        @Query("select c from Userrole c" +
                " WHERE c.userrolePK.role = ?1")
        Userrole[] SelectByRole(String role);
        
}
