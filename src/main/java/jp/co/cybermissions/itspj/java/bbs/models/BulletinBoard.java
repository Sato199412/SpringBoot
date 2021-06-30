package jp.co.cybermissions.itspj.java.bbs.models;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@Table(name = "bbs")
public class BulletinBoard {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // @NotBlank
    @Column(nullable = false)
    @DateTimeFormat(iso = ISO.DATE)
    private Date createDate;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Size(max = 100000)
    @Column(nullable = false)
    private String content;

    // public String getContent() {
       
    // }

    // public void setContent(String content) {
    //     if (content != null && !content.isEmpty()){
    //         this.content = content.replaceAll( "<br/>","\n");
    //     } else {
    //         this.content = content;
    //     }
    // }

    public String getBrContent() {
        if (content != null && !content.isEmpty()){
            return content.replaceAll("\n", "<br/>");
        } else {
            return content;
        } 
    }

    @NotBlank
    @Size(max = 100)
    private String createUser;
}
