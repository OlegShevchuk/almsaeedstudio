package almsaeedstudio.service;

import almsaeedstudio.domain.DataTables;
import almsaeedstudio.repository.DbWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by oleg on 3/9/17.
 */

@Service
public class DataService {

    @Autowired
    private DbWorker worker;

    public Integer create(DataTables dataTables) {
        if (dataTables.getId() != null) {
            dataTables.setId(null);
        }
        return worker.add(dataTables);
    }

    public List<DataTables> getAll() {
        List <DataTables> list = worker.getAll();
        return list;
    }

    public Map<String, List<DataTables>> groupeByRenderingEngine() {
        Map<String, List<DataTables>> groupe = new HashMap<>();
        getAll().forEach(dataTables -> {
            if (groupe.containsKey(dataTables.getRenderingEngine())) {
                groupe.get(dataTables.getRenderingEngine()).add(0, dataTables);
            } else {
                List<DataTables> list = new LinkedList<>();
                list.add(dataTables);
                groupe.put(dataTables.getRenderingEngine(), list);
            }
        });
        return groupe;
     }

    public DataTables update(Integer id, DataTables data) {
        worker.getById(id);
        data.setId(id);
        return worker.update(data);
    }

    public void del(Integer id) {
        worker.delete(worker.getById(id));
    }
}
