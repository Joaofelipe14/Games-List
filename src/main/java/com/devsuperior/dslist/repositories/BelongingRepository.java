package com.devsuperior.dslist.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dslist.entities.Belonging;

@Repository
public interface BelongingRepository extends JpaRepository<Belonging, Long> {

    @Query("DELETE FROM Belonging b WHERE b.id.game.id = :gameId AND b.id.list.id = :listId")
    void deleteByGameIdAndListId(@Param("gameId") Long gameId, @Param("listId") Long listId);

    boolean existsById_GameId(Long gameId);

}
