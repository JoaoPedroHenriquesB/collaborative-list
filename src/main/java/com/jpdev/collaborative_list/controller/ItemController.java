package com.jpdev.collaborative_list.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpdev.collaborative_list.dto.ItemRequest;
import com.jpdev.collaborative_list.entity.ItemEntity;
import com.jpdev.collaborative_list.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/{roomId}/{listId}/create")
    public ResponseEntity<ItemEntity> createItem(
            @PathVariable Integer roomId, 
            @PathVariable Integer listId, 
            @RequestBody ItemRequest request) {
        ItemEntity createdItem = itemService.createItem(roomId, listId, request);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }
    
    @GetMapping("/{listId}")
    public ResponseEntity<List<ItemEntity>> getItemsByListId(@PathVariable Integer listId) {
        List<ItemEntity> items = itemService.getItemsByListId(listId);
        return ResponseEntity.ok(items);
    }
    
    @GetMapping("/id/{itemId}")
    public ResponseEntity<ItemEntity> getItemById(@PathVariable Integer itemId) {
        ItemEntity item = itemService.getItemById(itemId);
        return ResponseEntity.ok(item);
    }
    
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer itemId) {
        itemService.deleteItem(itemId);
        return ResponseEntity.noContent().build();
    }
}
