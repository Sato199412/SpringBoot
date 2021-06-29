package jp.co.cybermissions.itspj.java.bbs.models;

// import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulletinBoardRepository extends JpaRepository<BulletinBoard, Long> {
    // public User findById(int id);
	// public void deleteById(int id);
}
