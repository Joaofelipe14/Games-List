package com.devsuperior.dslist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dslist.dto.BelongingDTO;
import com.devsuperior.dslist.services.BelongingService;
import com.devsuperior.dslist.entities.Belonging;

@RestController
@RequestMapping("/belongings")
public class BelongingController {

    @Autowired
    private BelongingService belongingService;

    @PostMapping
    public ResponseEntity<Belonging> addGameToList(@RequestBody BelongingDTO dto) {
        Belonging belonging = belongingService.addGameToList(dto.getGameId(), dto.getListId(), dto.getPosition());
        return ResponseEntity.status(201).body(belonging);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeGameFromList(@RequestParam Long gameId, @RequestParam Long listId) {
        belongingService.removeGameFromList(gameId, listId);
        return ResponseEntity.noContent().build();
    }
}
