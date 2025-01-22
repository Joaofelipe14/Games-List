package com.devsuperior.dslist.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslist.dto.GameCreateDTO;
import com.devsuperior.dslist.dto.GameDTO;
// import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.services.GameService;

@RestController
@RequestMapping(value = "/games")
public class GameController {

	@Autowired
	private GameService gameService;

	@GetMapping(value = "/{id}")
	public GameDTO findById(@PathVariable Long id) {
		GameDTO result = gameService.findById(id);
		return result;
	}

	@GetMapping
	public List<GameMinDTO> findAll() {
		List<GameMinDTO> result = gameService.findAll();
		return result;
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody GameCreateDTO dto) {
		GameDTO result = gameService.create(dto);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(Map.of(
						"message", "Jogo criado com sucesso!",
						"game", result));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		gameService.delete(id);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(Map.of(
						"message", "Jogo excluído com sucesso!"));
	}

}