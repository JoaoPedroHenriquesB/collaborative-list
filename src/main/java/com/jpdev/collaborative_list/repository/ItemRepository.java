package com.jpdev.collaborative_list.repository;

import com.jpdev.collaborative_list.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
    
    @Query("SELECT i FROM ItemEntity i WHERE i.list.id = :listId")
    List<ItemEntity> findByListId(@Param("listId") Integer listId);
}
