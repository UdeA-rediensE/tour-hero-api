package co.udea.api.hero.controller;

import co.udea.api.hero.dto.HeroDTO;
import co.udea.api.hero.model.Hero;
import co.udea.api.hero.service.HeroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiImplicitParam;
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

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Obtiene el héroe correspondiente al ID proporcionado",
            notes = "Este método busca un héroe en la base de datos con el ID proporcionado y devuelve un objeto de tipo `Hero` que contiene información sobre el héroe encontrado.",
            response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Héroe encontrado exitosamente"),
            @ApiResponse(code = 404, message = "No se encuentra el héroe con el id dado en la base de datos")})
    @ApiImplicitParam(name = "id", value = "ID del héroe a buscar", required = true, example = "1")

    public ResponseEntity<HeroDTO> getHero(@PathVariable Integer id) {
        log.info("Request GET /v1/heroes/{} - Buscando héroe por id", id);
        HeroDTO heroDto = heroService.getHero(id);
        return ResponseEntity.ok(heroDto);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "Busca todos los héroes de la base de datos",
            notes = "Este método devuelve una lista de todos los héroes encontrados en la base de datos.",
            response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Héroes encontrados exitosamente. Si la lista está vacía, significa que no hay héroes en la base de datos.")})
    public ResponseEntity<List<HeroDTO>> getHeroes() {
        log.info("Request GET /v1/heroes - Buscando todos los héroes");
        List<HeroDTO> heroDTOs = heroService.getHeroes();
        return ResponseEntity.ok(heroDTOs);
    }

    @GetMapping(params = "name")
    @ApiOperation(value = "Busca héroes por el término pasado como argumento",
            notes = "Busca todos los héroes que incluyan en su nombre el término pasado como argumento",
            response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Héroes encontrados exitosamente. Si la lista está vacía, significa que no hay héroes que coincidan con el término de búsqueda.")})
    @ApiImplicitParam(name = "name", value = "Término a buscar en el nombre del héroe", required = true, example = "man")
    public ResponseEntity<List<HeroDTO>> searchHeroes(@RequestParam("name") String term) {
        log.info("Request GET /v1/heroes - Buscando héroes que coincidan con el término '{}'", term);
        List<HeroDTO> heroDTOs = heroService.searchHeroes(term);
        return ResponseEntity.ok(heroDTOs);
    }

    @PutMapping("")
    @ApiOperation(value = "Actualizar el héroe del id pasado como argumento",
            notes = "Este método actualiza el nombre del héroe del id pasado como argumento",
            response = Hero.class, produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Héroe actualizado exitosamente"),
            @ApiResponse(code = 404, message = "No se encuentra el héroe con el id dado en la base de datos. No se pudo actualizar el héroe.")})
    public ResponseEntity<HeroDTO> updateHero(@RequestBody HeroDTO heroDto) {
        log.info("Request PUT /v1/heroes - Actualizando héroe de id: {}", heroDto.getId());
        HeroDTO updatedHeroDto = heroService.updateHero(heroDto);
        return ResponseEntity.ok(updatedHeroDto);
    }

    @PostMapping("")
    @ApiOperation(value = "Agregar nuevo héroe",
            notes = "Agregar un nuevo héroe con el nombre que se pasa como argumento y un ID autogenerado",
            response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Héroe agregado exitosamente")})
    public ResponseEntity<HeroDTO> addHero(@RequestBody HeroDTO heroDto) {
        log.info("Request POST /v1/heroes - Agregando héroe de nombre: {} e ID: {}", heroDto.getName(), heroDto.getId());
        HeroDTO newHeroDto = heroService.addHero(heroDto);
        return ResponseEntity.ok(newHeroDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar el héroe del id pasado como argumento",
            notes = "Eliminar de la base de datos el héroe del id pasado como argumento",
            response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "No se encuentra el héroe con el id dado en la base de datos. No se pudo eliminar el héroe.")})
    @ApiImplicitParam(name = "id", value = "ID del héroe a eliminar", required = true, example = "5")
    public ResponseEntity<Hero> deleteHero(@PathVariable Integer id) {
        log.info("Request DELETE /v1/heroes/{} - Eliminando héroe por id", id);
        heroService.deleteHero(id);
        return ResponseEntity.noContent().build();
    }
}
