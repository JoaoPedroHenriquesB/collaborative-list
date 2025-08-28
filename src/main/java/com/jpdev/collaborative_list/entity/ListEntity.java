package com.jpdev.collaborative_list.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lists")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idList", nullable = false)
    private Integer listId;

    @Column(name = "listName",nullable = false, length = 100)
    private String listName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idRoomFK", nullable = false)
    private RoomEntity room;

    @Builder.Default
    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEntity> items = new ArrayList<>();
}