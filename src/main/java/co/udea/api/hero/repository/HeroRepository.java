package co.udea.api.hero.repository;

import co.udea.api.hero.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {
    List<Hero> findAllByNameContainingIgnoreCase(String name);
    boolean existsHeroByName(String name);

}
