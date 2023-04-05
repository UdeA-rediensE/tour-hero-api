package co.udea.api.hero.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "heroes")
@Getter @Setter
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public Hero() {
    }

    public Hero(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
