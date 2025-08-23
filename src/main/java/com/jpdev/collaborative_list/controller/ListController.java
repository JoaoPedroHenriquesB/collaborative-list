package com.jpdev.collaborative_list.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpdev.collaborative_list.entity.ItemEntity;
import com.jpdev.collaborative_list.entity.ListEntity;
import com.jpdev.collaborative_list.service.ListService;

@RestController
@RequestMapping("/lists")
public class ListController {

    @Autowired
    private ListService listService;

    @GetMapping("/{roomId}")
    public ResponseEntity<List<ListEntity>> getListsByRoom(@PathVariable Integer roomId) {
        try {
            List<ListEntity> lists = listService.getListsByRoom(roomId);
            if (lists.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(lists);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{roomId}/create")
    public ResponseEntity<ListEntity> createList(@PathVariable Integer roomId, @RequestBody ListEntity list) {
        try {
            ListEntity newList = listService.createList(roomId, list);
            return ResponseEntity.status(HttpStatus.CREATED).body(newList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/id/{listId}")
    public ResponseEntity<ListEntity> getListById(@PathVariable Integer listId) {
        try {
            ListEntity list = listService.getListById(listId);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{listId}/items")
    public ResponseEntity<ItemEntity> createdItem(@PathVariable Integer listId, @RequestBody ItemEntity item) {
        try {
            ItemEntity newItem = listService.addItemToList(listId, item);
            return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    

}
