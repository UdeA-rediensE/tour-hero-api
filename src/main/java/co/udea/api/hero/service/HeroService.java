package co.udea.api.hero.service;

import co.udea.api.hero.exception.DataNotFoundException;
import co.udea.api.hero.model.Hero;
import co.udea.api.hero.repository.HeroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HeroService {

    private final Logger log = LoggerFactory.getLogger(HeroService.class);

    private final HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public Hero getHero(Integer id) {
        Optional<Hero> optionalHero = heroRepository.findById(id);
        if (!optionalHero.isPresent()) {
            log.info("No se encuentra un héroe con ID: " + id);
            throw new DataNotFoundException("El héroe no existe");
        }
        return optionalHero.get();
    }

    public Page<Hero> getHeroes(Pageable pageable) {
        Page<Hero> heroes = heroRepository.findAll(pageable);
        if (heroes.isEmpty()) {
            throw new DataNotFoundException("No se encontraron héroes");
        }else{
            return heroes;
        }
    }

    public Page<Hero> searchHeroes(String term, Pageable pageable) {
        return heroRepository.findAllByNameContainingIgnoreCase(term, pageable);
    }

    public Hero updateHero(Hero hero) {
        Optional<Hero> optionalHero = heroRepository.findById(hero.getId());
        if (!optionalHero.isPresent()) {
            log.info("No se encuentra un héroe con ID: " + hero.getId());
            throw new DataNotFoundException("No se puede actualizar el héroe porque no se encontró en la base de datos");
        } else {
            return heroRepository.save(hero);
        }
    }

    public Hero addHero(Hero hero) {
        return heroRepository.save(hero);
    }

    public void deleteHero(Integer id) {
        Optional<Hero> optionalHero = heroRepository.findById(id);
        if (!optionalHero.isPresent()) {
            log.info("No se encuentra un héroe con ID: " + id);
            throw new DataNotFoundException("El héroe no existe");
        } else {
            heroRepository.deleteById(id);
        }

    }
}
