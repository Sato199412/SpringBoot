package jp.co.cybermissions.itspj.java.bbs.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.cybermissions.itspj.java.bbs.models.BulletinBoard;
import jp.co.cybermissions.itspj.java.bbs.models.BulletinBoardRepository;
import lombok.RequiredArgsConstructor;

/**
 * 会員専用（要認証）URLのコントローラー
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("")
public class BulletinBoardController {
    
    private final BulletinBoardRepository rep;

    @GetMapping("")
    public String list(Model model){
        List<BulletinBoard> list = rep.findAll();
        model.addAttribute("postlist", list);
        return "bbs/list";
    }

    @GetMapping("/new")
    public String add(@ModelAttribute("bbs") BulletinBoard bbs){
        return "bbs/new";
    }

    @PostMapping("/")
    public String create(@Validated @ModelAttribute("bbs") BulletinBoard bbs, BindingResult result) {
        if (result.hasErrors()){
            return "bbs/new";
        }
        bbs.setCreateDate(new Date());
        rep.save(bbs);
        return "redirect:";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable long id, Model model){
        BulletinBoard bbs = rep.getById(id);
        model.addAttribute("bbs", bbs);
        return "bbs/edit";
    }

    @PatchMapping("/{id}/edit")
    public String update(@PathVariable long id, @Validated 
        @ModelAttribute("bbs") BulletinBoard bbs, BindingResult result,
        Model model,RedirectAttributes attrs) {
        if (result.hasErrors()){
            return "bbs/edit";
        }
        bbs.setCreateDate(new Date());
        bbs.setId(id);
        rep.save(bbs);
        return "redirect:/";
    }

    @GetMapping("/{id}/show")
    public String show(@PathVariable long id, Model model){
        BulletinBoard bbs = rep.getById(id);
        model.addAttribute("bbs", bbs);
        return "bbs/show";
    }

    @DeleteMapping("/{id}")
    public String destroy(@PathVariable long id){
        rep.deleteById(id);
        return "redirect:";
    }

}
