package app.repository;

import app.domain.entities.Hero;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class HeroRepositoryImpl implements HeroRepository {
    private final EntityManager entityManager;
@Inject
    public HeroRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Hero hero) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(hero);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public Hero findById(String id) {
        return this.entityManager.createQuery("SELECT h FROM Hero h WHERE h.id=:id",Hero.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    @Override
    public List<Hero> findAll() {
        return this.entityManager.createQuery("SELECT h FROM Hero h ORDER BY h.level desc ",Hero.class).getResultList();
    }

    @Override
    public void update(Hero hero) {
    this.entityManager.getTransaction().begin();
    this.entityManager.merge(hero);
this.entityManager.getTransaction().commit();
    }

    @Override
    public void delete(String id) {

        this.entityManager.getTransaction().begin();
        Hero hero=this.entityManager.find(Hero.class,id);
        this.entityManager.remove(hero);
        this.entityManager.getTransaction().commit();
    }
}
