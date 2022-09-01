package com.abdou.api.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Taxi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;
    private String model ;
    private String marque ;
    private String number;
    private String color ; 

    @ManyToOne()
    private  User owner;

    @JsonIgnore
    @OneToMany(mappedBy = "taxi")
    private List<Ride> rides = new ArrayList<>();

}
