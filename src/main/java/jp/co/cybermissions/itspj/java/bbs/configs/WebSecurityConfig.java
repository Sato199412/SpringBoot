package jp.co.cybermissions.itspj.java.bbs.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    // staticファイルはセキュリティ設定対象外としておく
    web.ignoring().antMatchers("/js/**", "/css/**", "/webjars/**", "/image/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        ///////////////////////////////////////////////
        // 未ログインでもアクセス可能なURLを指定する
        // ログイン・登録画面
        .antMatchers("/login", "/register").permitAll()
        // 公開URL
        .antMatchers("/bbs/{id}/show","/bbs").permitAll()
        // ★H2コンソールを使用する場合に指定する
        .antMatchers("/h2-console/**").permitAll()
        // 上記以外は認証が必要
        .anyRequest().authenticated().and()

        ///////////////////////////////////////////////
        // フォーム認証を有効にする
        .formLogin()
        // 自作ログイン画面のURL
        .loginPage("/login")
        // ログイン成功後に表示するURL
        .defaultSuccessUrl("/").and()

        ///////////////////////////////////////////////
        // ログアウト
        .logout()
        // ログアウトのURL
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        // ログアウト成功後に表示するURL
        .logoutSuccessUrl("/bbs");

    // ★H2コンソールを使用する場合に指定する
    // (本番環境では削除すべき)
    http.csrf().disable();
    http.headers().frameOptions().disable();
    //
  }

  /** ログイン認証時のセキュリティ設定 */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // UserDetailsServiceを使用してDBからユーザーを参照する
    auth.userDetailsService(userDetailsService)
        // パスワードエンコーダ指定
        .passwordEncoder(passwordEncoder());
  }
}
