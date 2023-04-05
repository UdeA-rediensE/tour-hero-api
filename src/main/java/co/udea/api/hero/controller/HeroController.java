package co.udea.api.hero.controller;

import co.udea.api.hero.model.Hero;
import co.udea.api.hero.repository.HeroRepository;
import co.udea.api.hero.service.HeroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiResponses(value = {
        @ApiResponse(code = 400, message = "Petición inválida"),
        @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")
})
@RestController
@RequestMapping("/v1/heroes")
public class HeroController {

    private final Logger log = LoggerFactory.getLogger(HeroController.class);

    private final HeroService heroService;
    private final HeroRepository heroRepository;

    public HeroController(HeroService heroService,
                          HeroRepository heroRepository) {
        this.heroService = heroService;
        this.heroRepository = heroRepository;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Busca un hero por su id", response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Héroe encontrado exitosamente"),
            @ApiResponse(code = 404, message = "Héroe no encontrado")})

    public ResponseEntity<Hero> getHero(@PathVariable Integer id) {
        log.info("Rest request buscar héroe por id: " + id);
        return ResponseEntity.ok(heroService.getHero(id));
    }

    @GetMapping("")
    @ApiOperation(value = "Busca todos los héroes de la base de datos", response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Héroes encontrados exitosamente")})

    public ResponseEntity<List<Hero>> getHeroes() {
        log.info("Rest request buscar todos los héroes paginados");
        List<Hero> heroes = heroService.getHeroes();
        return ResponseEntity.ok(heroes);
    }

    @GetMapping(params = "name")
    @ApiOperation(value = "Busca todos los héroes con el termino dado ", response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Héroes encontrados exitosamente")})
    public ResponseEntity<List<Hero>> searchHeroes(@RequestParam("name") String term ) {
        log.info("Rest request buscar héroes que coincidan con el término: " + term);
        List<Hero> heroes = heroService.searchHeroes(term);
        return ResponseEntity.ok(heroes);
    }

    @PutMapping("")
    @ApiOperation(value = "Actualizar héroe con el id dado", response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Héroes actualizado exitosamente"),
            @ApiResponse(code = 404, message = "No se encuentra el héroe con el id dado")})
    public ResponseEntity<Hero> updateHero(@RequestBody Hero hero){
        log.info("Rest request actualizar héroe de id: " + hero.getId());
        // Crear una nueva instancia de Hero con el ID del objeto original
        Hero updatedHero = new Hero(hero.getId(), hero.getName());
        // Actualizar los datos del objeto
        updatedHero.setName(hero.getName());
        updatedHero = heroService.updateHero(updatedHero);
        return ResponseEntity.ok(updatedHero);
    }

    @PostMapping("")
    @ApiOperation(value = "Agregar héroe con el nombre dado", response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Héroes agregado exitosamente")})
    public ResponseEntity<Hero> addHero(@RequestBody Hero hero){
        log.info("Rest request agregar el héroe de nombre: "+ hero.getName() + "e ID: " + hero.getId());
        Hero newHero = heroService.addHero(hero);
        return ResponseEntity.ok(newHero);
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar héroe con el id dado", response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Héroes eliminado exitosamente")})
    public ResponseEntity<?> deleteHero(@PathVariable Integer id){
        log.info("Rest request eliminar héroe por id: " + id);
        heroService.deleteHero(id);
        return ResponseEntity.noContent().build();
    }
}
