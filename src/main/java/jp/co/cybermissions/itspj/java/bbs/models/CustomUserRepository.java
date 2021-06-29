package jp.co.cybermissions.itspj.java.bbs.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser,Long>{
    
    CustomUser findByUsername(String username);
}
