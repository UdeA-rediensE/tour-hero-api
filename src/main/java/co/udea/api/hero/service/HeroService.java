package co.udea.api.hero.service;

import co.udea.api.hero.exception.DataNotFoundException;
import co.udea.api.hero.model.Hero;
import co.udea.api.hero.dto.HeroDTO;
import co.udea.api.hero.repository.HeroRepository;
import co.udea.api.hero.mapper.HeroMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HeroService {

    private static final String HERO_NOT_FOUND = "No se encontró el héroe con ID: {}";
    private final Logger log = LoggerFactory.getLogger(HeroService.class);

    private final HeroRepository heroRepository;
    private final HeroMapper heroMapper;

    public HeroService(HeroRepository heroRepository, HeroMapper heroMapper) {
        this.heroRepository = heroRepository;
        this.heroMapper = heroMapper;
    }

    public HeroDTO getHero(Integer id) {
        Optional<Hero> optionalHero = heroRepository.findById(id);
        if (!optionalHero.isPresent()) {
            log.info(HERO_NOT_FOUND, id);
            throw new DataNotFoundException("El héroe no existe");
        } else {
            Hero hero = optionalHero.get();
            return heroMapper.toHeroDTO(hero);
        }
    }

    public List<HeroDTO> getHeroes() {
        List<Hero> heroes = heroRepository.findAll();
        if (heroes.isEmpty()) {
            throw new DataNotFoundException("No se encontraron héroes");
        } else {
            List<HeroDTO> heroDTOs = new ArrayList<>();
            for (Hero hero : heroes) {
                heroDTOs.add(heroMapper.toHeroDTO(hero));
            }
            return heroDTOs;
        }
    }

    public List<HeroDTO> searchHeroes(String term) {
        List<Hero> heroes = heroRepository.findAllByNameContainingIgnoreCase(term);
        List<HeroDTO> heroDTOs = new ArrayList<>();
        for (Hero hero : heroes) {
            heroDTOs.add(heroMapper.toHeroDTO(hero));
        }
        return heroDTOs;
    }

    public HeroDTO updateHero(HeroDTO heroDto) {
        Optional<Hero> optionalHero = Optional.ofNullable(heroRepository.findByName(heroDto.getName()));
        if (optionalHero.isPresent()) {
            log.info("No se puede actualizar porque el heroe {} ya existe", heroDto.getName());
            throw new IllegalArgumentException("El 1111héroe ya existe en la base de datos.");
        } else if (heroDto.getName() == null || heroDto.getName().equals("")) {
            throw new IllegalArgumentException("El nombre del héroe no puede ser nulo.");
        } else {
            Hero hero = heroMapper.toHero(heroDto);
            hero = heroRepository.save(hero);
            return heroMapper.toHeroDTO(hero);
        }
    }

    public HeroDTO addHero(HeroDTO heroDto) {
        Optional<Hero> optionalHero = Optional.ofNullable(heroRepository.findByName(heroDto.getName()));
        if (optionalHero.isPresent()) {
            log.info("No se puede agregar porque el heroe {} ya existe con id {}", heroDto.getName(), optionalHero.get().getId());
            throw new IllegalArgumentException("El héroe ya existe en la base de datos.");
        }else {
            Hero hero = heroMapper.toHero(heroDto);
            hero = heroRepository.save(hero);
            return heroMapper.toHeroDTO(hero);
        }
    }

    public void deleteHero(Integer id) {
        Optional<Hero> optionalHero = heroRepository.findById(id);
        if (!optionalHero.isPresent()) {
            log.info(HERO_NOT_FOUND, id);
            throw new DataNotFoundException("El héroe no existe");
        } else {
            heroRepository.deleteById(id);
        }
    }
}
