package co.udea.api.hero.service;

import co.udea.api.hero.exception.DataNotFoundException;
import co.udea.api.hero.model.Hero;
import co.udea.api.hero.repository.HeroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService {

    private final Logger log = LoggerFactory.getLogger(HeroService.class);

    private HeroRepository heroRepository;

    @Autowired
    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public Hero getHero(Integer id) {
        Optional<Hero> optionalHero = heroRepository.findById(id);
        if (!optionalHero.isPresent()) {
            log.info("No se encuentra un héroe con ID: " + id);
            throw new DataNotFoundException("El héroe no existe");
        } else {
            return optionalHero.get();
        }
    }


    public List<Hero> getHeroes() {
        List<Hero> heroes = heroRepository.findAll();
        if (heroes.isEmpty()) {
            throw new DataNotFoundException("No se encontraron héroes");
        } else {
            return heroes;
        }
    }

    public List<Hero> searchHeroes(String term) {
        return heroRepository.findAllByNameContainingIgnoreCase(term);
    }

    public Hero updateHero(Hero hero) {
        Optional<Hero> optionalHero = heroRepository.findById(hero.getId());
        if (!optionalHero.isPresent()) {
            log.info("No se encuentra un héroe con ID: " + hero.getId());
            throw new DataNotFoundException("No se puede actualizar el héroe porque no se encontró en la base de datos");
        } else if (hero.getName() == null || hero.getName().equals("")) {
            throw new IllegalArgumentException("El nombre del héroe no puede ser nulo.");
        } else {
            return heroRepository.save(hero);
        }
    }

    public Hero addHero(Hero hero) {
        // Verificar si el nombre del hero ya existe
        if (heroRepository.existsHeroByName(hero.getName())) {
            throw new IllegalArgumentException("El héroe ya existe en la base de datos.");
        } else {
            return heroRepository.save(hero);
        }
    }

    public void deleteHero(Integer id) {
        Optional<Hero> optionalHero = heroRepository.findById(id);
        if (!optionalHero.isPresent()) {
            log.info("No se encuentra un héroe con ID: " + id);
            throw new IllegalArgumentException("El héroe no existe");
        } else {
            heroRepository.deleteById(id);
        }
    }
}
