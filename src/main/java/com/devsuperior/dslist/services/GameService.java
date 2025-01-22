package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.devsuperior.dslist.dto.GameCreateDTO;
import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;
import com.devsuperior.dslist.exceptions.ResourceNotFoundException;
import com.devsuperior.dslist.repositories.BelongingRepository;


@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
    private BelongingRepository belongingRepository;
	
	@Transactional(readOnly = true)
	public GameDTO findById(@PathVariable Long listId) {
		Game result = gameRepository.findById(listId).get();
		return new GameDTO(result);
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll() {
		List<Game> result = gameRepository.findAll();
		return result.stream().map(GameMinDTO::new).toList();
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByGameList(Long listId) {
		List<GameMinProjection> games = gameRepository.searchByList(listId);
		return games.stream().map(GameMinDTO::new).toList();
	}

	  @Transactional
    public GameDTO create(GameCreateDTO dto) {

		Game entity = new Game();
        entity.setTitle(dto.getTitle());
        entity.setYear(dto.getYear());
        entity.setGenre(dto.getGenre());
        entity.setPlatforms(dto.getPlatforms());
        entity.setScore(dto.getScore());
        entity.setImgUrl(dto.getImgUrl());
        entity.setShortDescription(dto.getShortDescription());
        entity.setLongDescription(dto.getLongDescription());

        entity = gameRepository.save(entity);

        return new GameDTO(entity);
    }

	public void delete(Long id) {

		if (belongingRepository.existsById_GameId(id)) {
            throw new ResourceNotFoundException("Não é possível excluir o jogo com o id: " + id + " porque ele está vinculado a uma lista.");
        }

        if (!gameRepository.existsById(id)) {
			throw new ResourceNotFoundException("Jogo não encontrado com o id: " + id);
        }

        gameRepository.deleteById(id);
    }
}