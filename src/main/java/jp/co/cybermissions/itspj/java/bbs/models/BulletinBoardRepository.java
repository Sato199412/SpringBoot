package jp.co.cybermissions.itspj.java.bbs.models;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface BulletinBoardRepository extends JpaRepository<BulletinBoard, Long> {

    // @Query("SELECT * FROM BBS ORDER BY CREATE_DATE DESC")
    // List<BulletinBoard> findAllOderByCreateDateDesc();
}
