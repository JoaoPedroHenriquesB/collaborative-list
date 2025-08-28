package com.jpdev.collaborative_list.service;

import org.springframework.transaction.annotation.Transactional;

import com.jpdev.collaborative_list.entity.ListEntity;
import com.jpdev.collaborative_list.entity.RoomEntity;
import com.jpdev.collaborative_list.exception.ListNotFound;
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
        roomRepository.findById(roomId)
            .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + roomId));
        return listRepository.findByRoomRoomID(roomId);
    }

    
    @Transactional(readOnly = true)
    public ListEntity getListById(Integer listId) {
        return listRepository.findById(listId)
                .orElseThrow(() -> new ListNotFound("List not found with id: " + listId));
    }

    

}