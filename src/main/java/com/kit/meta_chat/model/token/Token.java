package com.kit.meta_chat.model.token;


import com.kit.meta_chat.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token extends BaseEntity {

    @Column(name = "token", nullable = false, length = 500)
    private String token;
    @Column(name = "exp_date", nullable = false)
    private Date expDate;

    @PrePersist
    public void preCreate(){
        this.setCreate_at(new Date());
        this.setUpdate_at(new Date());
    }
}
