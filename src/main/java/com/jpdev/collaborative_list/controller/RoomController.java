package com.jpdev.collaborative_list.controller;

import com.jpdev.collaborative_list.dto.CreateRoomRequest;
import com.jpdev.collaborative_list.dto.JoinRoomRequest;
import com.jpdev.collaborative_list.entity.RoomEntity;
import com.jpdev.collaborative_list.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/create")
    public ResponseEntity<RoomEntity> createRoom(@Valid @RequestBody CreateRoomRequest request) {
        RoomEntity newRoom = roomService.createRoom(request);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    @DeleteMapping("/{roomId}/delete")
    public ResponseEntity<Void> deleteRoom(@PathVariable Integer roomId) {
        try {
            roomService.deleteRoom(roomId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<RoomEntity>> getAllRooms() {
        List<RoomEntity> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @PostMapping("/join")
    public ResponseEntity<RoomEntity> joinRoom(@Valid @RequestBody JoinRoomRequest request) {
        try {
            RoomEntity room = roomService.getRoomByCode(request.getRoomCode());
            return ResponseEntity.ok(room);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{roomId}/code")
    public ResponseEntity<String> getRoomCode(@PathVariable Integer roomId) {
        try {
            String code = roomService.getRoomCode(roomId);
            return ResponseEntity.ok(code);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{roomId}")
    public ResponseEntity<RoomEntity> getRoomById(@PathVariable Integer roomId) {
        try {
            RoomEntity room = roomService.getRoomById(roomId);
            return ResponseEntity.ok(room);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}