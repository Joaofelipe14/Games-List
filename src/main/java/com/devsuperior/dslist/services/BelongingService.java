package com.devsuperior.dslist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.entities.Belonging;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.exceptions.ResourceNotFoundException;
import com.devsuperior.dslist.repositories.BelongingRepository;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;

@Service
public class BelongingService {

    @Autowired
    private BelongingRepository belongingRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameListRepository gameListRepository;

    @Transactional
    public Belonging addGameToList(Long gameId, Long listId, Integer position) {
        Game game = gameRepository.findById(gameId)
            .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado com o ID: " + gameId));
        
        GameList gameList = gameListRepository.findById(listId)
            .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada com o ID: " + listId));

        Belonging belonging = new Belonging();
        belonging.setGame(game);
        belonging.setList(gameList);
        belonging.setPosition(position);

        return belongingRepository.save(belonging);
    }

    @Transactional
    public void removeGameFromList(Long gameId, Long listId) {
        belongingRepository.deleteByGameIdAndListId(gameId, listId);
    }
}
