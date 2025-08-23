package com.jpdev.collaborative_list.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemId")
    private Integer id;

    @Column(name = "itemName", nullable = false, length = 100)
    private String name;
    
    @Column(name = "itemDescription", length = 500)
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idListFK", nullable = false)
    private ListEntity list;
}
