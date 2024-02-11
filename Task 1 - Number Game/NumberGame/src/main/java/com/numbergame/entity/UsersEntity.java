package com.numbergame.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "users_data")
public class UsersEntity {
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username")
    private String username;

    public UsersEntity(String username){
        this.username = username;
    }
}
