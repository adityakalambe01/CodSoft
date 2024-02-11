package com.numbergame.repository;

import com.numbergame.entity.GameModeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameModeRepository extends JpaRepository<GameModeEntity, Long> {
    GameModeEntity findByGameLevelName(String levelName);
}
