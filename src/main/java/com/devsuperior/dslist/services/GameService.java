package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dslist.repositories.GameRepository;
import com.devsuperior.dslist.dto.GameMinDTO;


@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameMinDTO> findAll() {
        var result = gameRepository.findAll();

       return  result.stream().map(x -> new GameMinDTO(x)).toList();

    


    }
}
