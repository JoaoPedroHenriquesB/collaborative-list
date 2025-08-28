package com.jpdev.collaborative_list.service;

import com.jpdev.collaborative_list.dto.CreateRoomRequest;
import com.jpdev.collaborative_list.entity.RoomEntity;
import com.jpdev.collaborative_list.exception.RoomNotFoundException;
import com.jpdev.collaborative_list.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(RoomService.class);
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;
    private static final Random RANDOM = new SecureRandom();

    public String generateRoomCode() {
        StringBuilder roomCode = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            roomCode.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        String generatedCode = roomCode.toString();
        logger.info("Generated room code: {}", generatedCode);
        return generatedCode;
    }

    @Transactional
    public RoomEntity createRoom(CreateRoomRequest request) {
        String roomCode = generateRoomCode();

        RoomEntity newRoom = RoomEntity.builder()
                .roomCode(roomCode)
                .creatorName(request.getCreatorName())
                .build();

        return roomRepository.save(newRoom);
    }

    @Transactional(readOnly = true)
    public List<RoomEntity> getAllRooms() {
        return roomRepository.findAll();
    }

    @Transactional
    public void deleteRoom(Integer roomId) {
        RoomEntity room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + roomId));

        roomRepository.delete(room);
    }

    @Transactional(readOnly = true)
    public RoomEntity getRoomById(Integer roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + roomId));
    }

    @Transactional(readOnly = true)
    public RoomEntity getRoomByCode(String roomCode) {
        return roomRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with code: " + roomCode));
    }

    @Transactional(readOnly = true)
    public String getRoomCode(Integer roomId) {
        RoomEntity room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + roomId));
        return room.getRoomCode();
    }

    @Transactional(readOnly = true)
    public boolean roomExists(Integer roomId) {
        return roomRepository.existsById(roomId);
    }
}