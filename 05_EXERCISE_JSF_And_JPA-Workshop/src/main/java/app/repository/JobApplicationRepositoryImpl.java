package app.repository;

import app.domain.entities.JobApplication;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import java.util.List;

public class JobApplicationRepositoryImpl implements JobApplicationRepository {
    private final EntityManager entityManager;


    @Inject
    public JobApplicationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;

    }

    @Override
    public void save(JobApplication jpbApplication) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(jpbApplication);
        this.entityManager.getTransaction().commit();

    }

    @Override
    public JobApplication findById(String id) {
        return this.entityManager
                .createQuery("SELECT j FROM JobApplication j WHERE j.id=:id", JobApplication.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<JobApplication> findAll() {
        return this.entityManager.createQuery("SELECT j FROM JobApplication j", JobApplication.class).getResultList();
    }

    @Override
    public void update(JobApplication entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void delete(String id) {
        this.entityManager.getTransaction().begin();
        //this.entityManager.createQuery("DELETE FROM JobApplication j WHERE j.id=:id", JobApplication.class)
         //       .setParameter("id", id)
         //       .executeUpdate();
        JobApplication jobApplication=this.entityManager.find(JobApplication.class,id);
        this.entityManager.remove(jobApplication);
        this.entityManager.getTransaction().commit();

        }
    }

