package jp.co.cybermissions.itspj.java.bbs.Controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.cybermissions.itspj.java.bbs.models.CustomUser;
import jp.co.cybermissions.itspj.java.bbs.models.CustomUserRepository;
import jp.co.cybermissions.itspj.java.bbs.models.Role;
import lombok.RequiredArgsConstructor;

/**
 * 認証処理コントローラー
 */
@Controller
@RequiredArgsConstructor
public class AuthController {
    
    //パスワードエンコーダー
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomUserRepository userRep;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("user") CustomUser user) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String createUser(@Validated @ModelAttribute("user") CustomUser user, BindingResult result) {
        if (result.hasErrors()) {
            return "auth/register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole(user.isAdmin() ? Role.ROLE_ADMIN.name() : Role.ROLE_USER.name());

        userRep.save(user);

        return "redirect:/login?register";
    }

}
