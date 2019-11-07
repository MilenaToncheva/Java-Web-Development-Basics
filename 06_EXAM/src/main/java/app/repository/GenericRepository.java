package app.repository;

import java.util.List;

public interface GenericRepository<E,ID> {
    void save(E entity);

    E findById(ID id);

    List<E> findAll();

    void update(E entity);

    void delete (ID id);
}
