package jp.co.cybermissions.itspj.java.bbs.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.cybermissions.itspj.java.bbs.models.BulletinBoard;
import jp.co.cybermissions.itspj.java.bbs.models.BulletinBoardRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/bbs")
public class PublicBulletinBoardController {
    
    private final BulletinBoardRepository rep;

    @GetMapping("")
    public String list(Model model){
        List<BulletinBoard> list = rep.findAll();
        model.addAttribute("postlist", list);
        return "pub/list";
    }

    @GetMapping("/{id}/show")
    public String show(@PathVariable long id, Model model){
        BulletinBoard bbs = rep.getById(id);
        model.addAttribute("bbs", bbs);
        return "pub/show";
    }
}
