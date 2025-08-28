package com.jpdev.collaborative_list.repository;

import com.jpdev.collaborative_list.entity.RoomEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

    Optional<RoomEntity> findByRoomCode(String roomCode);
}
