package com.jpdev.collaborative_list.repository;

import com.jpdev.collaborative_list.entity.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<ListEntity, Integer> {
}
