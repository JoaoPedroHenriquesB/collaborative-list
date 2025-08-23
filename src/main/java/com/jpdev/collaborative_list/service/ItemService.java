package com.jpdev.collaborative_list.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpdev.collaborative_list.dto.AddItemRequest;
import com.jpdev.collaborative_list.entity.ItemEntity;
import com.jpdev.collaborative_list.entity.ListEntity;
import com.jpdev.collaborative_list.exception.ItemNotFoundException;
import com.jpdev.collaborative_list.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ListService listService;

    public ItemService(ItemRepository itemRepository, ListService listService) {
        this.itemRepository = itemRepository;
        this.listService = listService;
    }

    @Transactional
    public ItemEntity createItem(Integer roomId, Integer listId, AddItemRequest request) {
        ListEntity list = listService.getListById(listId);

        ItemEntity item = ItemEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .list(list)
                .build();

        return itemRepository.save(item);
    }

    public List<ItemEntity> getItemsByListId(Integer listId) {
        listService.getListById(listId);
        return itemRepository.findByListId(listId);
    }

    public ItemEntity getItemById(Integer itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with id: " + itemId));
    }

    @Transactional
    public void deleteItem(Integer itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new ItemNotFoundException("Item not found with id: " + itemId);
        }
        itemRepository.deleteById(itemId);
    }
}
