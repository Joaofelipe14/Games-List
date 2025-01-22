package com.devsuperior.dslist.dto;

public class BelongingDTO {

    private Long gameId;
    private Long listId;
    private Integer position;

    public BelongingDTO() {
    }

    public BelongingDTO(Long gameId, Long listId, Integer position) {
        this.gameId = gameId;
        this.listId = listId;
        this.position = position;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
