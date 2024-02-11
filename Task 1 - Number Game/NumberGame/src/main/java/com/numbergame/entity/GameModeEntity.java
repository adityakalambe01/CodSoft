package com.numbergame.entity;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "game_mode")
public class GameModeEntity {
    @Id
    @Column(name = "level_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameLevelId;

    @Column(name = "level_name")
    private String gameLevelName;

    @Column(name = "level_chances")
    private int gameLevelChances;

    @Column(name = "access_link")
    private String accessLink;

    public GameModeEntity(String gameLevelName, int gameLevelChances) {
        this.gameLevelName = gameLevelName;
        this.gameLevelChances = gameLevelChances;
    }

    public GameModeEntity(String gameLevelName, int gameLevelChances, String accessLink) {
        this.gameLevelName = gameLevelName;
        this.gameLevelChances = gameLevelChances;
        this.accessLink = accessLink;
    }
}
