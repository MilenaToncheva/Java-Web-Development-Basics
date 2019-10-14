package metube.repository;

import metube.domain.entities.Tube;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TubeRepositoryImpl implements TubeRepository {
    private EntityManager entityManager;

    public TubeRepositoryImpl() {
        this.entityManager = Persistence.createEntityManagerFactory("metube").createEntityManager();
    }

    @Override
    public Optional<Tube> findByName(String name) {
        try {
            Optional<Tube> tube = Optional.of(this.entityManager.createQuery("SELECT t FROM Tube t WHERE t.name=:tubeName", Tube.class)
                    .setParameter("tubeName", name).getSingleResult());

            return tube;
        } catch (NoResultException nre) {
            return Optional.empty();

        }

    }

    @Override
    public Tube save(Tube entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public List<Tube> findAll() {

        List<Tube> tubes = this.entityManager.createQuery("SELECT p FROM Tube p ", Tube.class).getResultList();


        return tubes;
    }

    @Override
    public Optional findById(String id) {
        Optional<Tube> tube = Optional.of(this.entityManager.createQuery("SELECT t FROM Tube t WHERE t.id=:tubeId", Tube.class)
                .setParameter("tubeId", id)
                .getSingleResult());
        return tube;
    }
}
