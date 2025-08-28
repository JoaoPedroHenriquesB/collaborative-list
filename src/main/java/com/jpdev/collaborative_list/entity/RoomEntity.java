package com.jpdev.collaborative_list.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomID")
    private Integer roomID;

    @Column(name = "roomCode", nullable = false, unique = true, length = 10)
    private String roomCode;

    @Column(name = "creatorName", nullable = false, length = 100)
    private String creatorName;

    @Builder.Default
    @Column(name = "creationTime", nullable = false)
    private LocalDateTime creationTime = LocalDateTime.now();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ListEntity> lists = new ArrayList<>();
}