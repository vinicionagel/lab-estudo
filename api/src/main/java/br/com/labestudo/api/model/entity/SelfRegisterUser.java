package br.com.labestudo.api.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "self_register_user")
@Data
public class SelfRegisterUser implements Serializable {

    @Id
    private String id;

    private String name;

    private String email;

    private String pass;

    @Column(name = "user_hash")
    private String userHash;

    @CreationTimestamp
    private LocalDateTime created;

}
