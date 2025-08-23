package com.jpdev.collaborative_list.service;

import org.springframework.transaction.annotation.Transactional;

import com.jpdev.collaborative_list.entity.ItemEntity;
import com.jpdev.collaborative_list.entity.ListEntity;
import com.jpdev.collaborative_list.entity.RoomEntity;
import com.jpdev.collaborative_list.exception.RoomNotFoundException;
import com.jpdev.collaborative_list.repository.ListRepository;
import com.jpdev.collaborative_list.repository.RoomRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ListService {

    private final RoomRepository roomRepository;
    private final ListRepository listRepository;

    public ListService(RoomRepository roomRepository, ListRepository listRepository) {
        this.roomRepository = roomRepository;
        this.listRepository = listRepository;
    }

    @Transactional
    public ListEntity createList(Integer roomId, ListEntity list) {
        RoomEntity room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + roomId));

        list.setRoom(room);
        room.getLists().add(list);
        return list;
    }

    @Transactional(readOnly = true)
    public List<ListEntity> getListsByRoom(Integer roomId) {
        RoomEntity room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + roomId));

        // Retorna lista vazia se não houver listas, evitando null
        return room.getLists() != null ? room.getLists() : List.of();
    }
    
    @Transactional(readOnly = true)
    public ListEntity getListById(Integer listId) {
        return listRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("List not found with id: " + listId));
    }

    public ItemEntity addItemToList(Integer listId, ItemEntity item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addItemToList'");
    }

}
