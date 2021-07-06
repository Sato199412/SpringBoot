package jp.co.cybermissions.itspj.java.bbs.models;



// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BulletinBoardRepository extends JpaRepository<BulletinBoard, Long> {

    
}
