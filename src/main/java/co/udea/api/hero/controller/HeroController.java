package co.udea.api.hero.controller;

import co.udea.api.hero.model.Hero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    @GetMapping("{id}")
    ResponseEntity<Hero> getHero(@PathVariable Integer id){
        return ResponseEntity.ok(new Hero(1, "Spider-Man "+id));
    }
}
