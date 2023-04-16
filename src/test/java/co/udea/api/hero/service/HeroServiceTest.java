package co.udea.api.hero.service;

import co.udea.api.hero.model.Hero;
import co.udea.api.hero.repository.HeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HeroServiceTest {

    @Mock
    private HeroRepository heroRepository;
    @InjectMocks
    private HeroService heroService;
    private Hero hero;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hero = new Hero(1, "Superman");
    }

    @Test
    void getHero() {
        when(heroRepository.findById(1)).thenReturn(Optional.of(hero));
        Hero result = heroService.getHero(1);
        assertEquals(hero, result);
    }

    @Test
    void getHeroes() {
        when(heroRepository.findAll()).thenReturn(Collections.singletonList(hero));
        List<Hero> expected = Collections.singletonList(hero);
        List<Hero> result = heroService.getHeroes();
        assertArrayEquals(expected.toArray(), result.toArray());
    }
}
