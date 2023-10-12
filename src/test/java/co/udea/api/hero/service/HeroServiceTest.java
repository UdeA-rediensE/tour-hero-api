package co.udea.api.hero.service;

import co.udea.api.hero.dto.HeroDTO;
import co.udea.api.hero.exception.DataNotFoundException;
import co.udea.api.hero.mapper.HeroMapper;
import co.udea.api.hero.model.Hero;
import co.udea.api.hero.repository.HeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HeroServiceTest {

    @InjectMocks
    private HeroService heroService;

    @Mock
    private HeroRepository heroRepository;

    @Mock
    private HeroMapper heroMapper;

    private Hero hero;
    private HeroDTO heroDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configuración de datos de prueba
        hero = new Hero(1, "Superman");
        heroDTO = new HeroDTO(1, "Superman");
    }

    @Test
    void testGetHero() {
        // Arrange
        when(heroRepository.findById(hero.getId())).thenReturn(Optional.of(hero));
        when(heroMapper.toHeroDTO(hero)).thenReturn(heroDTO);

        // Act
        HeroDTO result = heroService.getHero(hero.getId());

        // Assert
        assertEquals(heroDTO, result);
    }

    @Test
    void testGetHero_NotFound() {
        // Arrange
        when(heroRepository.findById(hero.getId())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(DataNotFoundException.class, () -> {
            heroService.getHero(hero.getId());
        });
    }
}
