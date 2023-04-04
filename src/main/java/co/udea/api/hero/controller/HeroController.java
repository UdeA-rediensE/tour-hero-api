package co.udea.api.hero.controller;

import co.udea.api.hero.model.Hero;
import co.udea.api.hero.service.HeroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final Logger log = LoggerFactory.getLogger(HeroController.class);

    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Busca un hero por su id", response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Héroe encontrado exitosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})

    public ResponseEntity<Hero> getHero(@PathVariable Integer id) {
        log.info("Rest request buscar héroe por id: " + id);
        return ResponseEntity.ok(heroService.getHero(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<Hero>> getHeroes(Pageable pageable) {
        log.info("Rest request buscar todos los héroes paginados");
        Page<Hero> heroes = heroService.getHeroes(pageable);
        return ResponseEntity.ok(heroes);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Hero>> searchHeroes(@RequestParam(value = "term") String term, Pageable pageable) {
        log.info("Rest request buscar héroes que coincidan con el término: " + term);
        Page<Hero> heroes = heroService.searchHeroes(term, pageable);
        return ResponseEntity.ok(heroes);
    }



}
