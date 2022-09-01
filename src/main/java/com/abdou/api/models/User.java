package com.abdou.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
 import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User   {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id ;
        private  String username ;
        private  String email ;
        private String password ;

        @ManyToMany(fetch = FetchType.EAGER)
        private Collection<Role> roles = new ArrayList<>();


        @JsonIgnore
        @OneToMany(mappedBy = "owner")
        private  Collection<Taxi> cars = new ArrayList<>();


        @Override
        public String toString() {
                return "User{" +
                        "id=" + id +
                        ", username='" + username + '\'' +
                        ", email='" + email + '\'' +
                        ", password='" + password + '\'' +
                        ", roles=" + roles +
                        '}';
        }
}
