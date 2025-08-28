package com.jpdev.collaborative_list.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idItem", nullable = false)
    private Integer itemId;

    @Column(name = "itemName", nullable = false, length = 200)
    private String itemName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idListFk", nullable = false)
    private ListEntity list;
}