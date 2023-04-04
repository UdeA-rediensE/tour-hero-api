package co.udea.api.hero.repository;

import co.udea.api.hero.model.Hero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {
    Page<Hero> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

}
