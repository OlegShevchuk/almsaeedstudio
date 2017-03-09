package almsaeedstudio.repository;

import almsaeedstudio.domain.DataTables;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by oleg on 3/9/17.
 */

@Repository
public class DbWorker {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Integer add(DataTables dataTables) {
        entityManager.persist(dataTables);
        return dataTables.getId();
    }

    public List<DataTables> getAll() {
       return entityManager.createNamedQuery("DataTables.findAll", DataTables.class).getResultList();
    }

    public DataTables getById(Integer id) {
        return entityManager.createNamedQuery("DataTables.fingById", DataTables.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    public DataTables update(DataTables dataTables) {
        return entityManager.merge(dataTables);
    }

    @Transactional
    public void delete(DataTables dataTables) {
        entityManager.remove(dataTables);
    }
}
