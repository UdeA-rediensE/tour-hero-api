package co.udea.api.hero.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "heroes")
@Getter @Setter
public class Hero {

    @Id
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;

    public Hero() {
    }

    public Hero(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
