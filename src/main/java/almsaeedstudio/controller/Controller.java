package almsaeedstudio.controller;

import almsaeedstudio.domain.DataTables;
import almsaeedstudio.service.DataService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by oleg on 3/9/17.
 */

@RestController
@RequestMapping("api")
public class Controller {

    private Logger logger = Logger.getLogger(Controller.class);

    @Autowired
    private DataService service;

    @PostMapping(value = "/create", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Integer create(@RequestBody DataTables data) {
        return service.create(data);
    }

    @GetMapping(value = "/getAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DataTables>> findAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "/getByRenderingEngine", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, List<DataTables>>> getGroupByRenderingEngine() {
        return ResponseEntity.ok(service.groupeByRenderingEngine());
    }

    @PostMapping(value = "/update/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DataTables> update(@PathVariable("id") Integer id, @RequestBody DataTables dataTables) {
        return ResponseEntity.ok(service.update(id, dataTables));
    }

    @GetMapping(value = "/del/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void del(@PathVariable("id") Integer id) {
        service.del(id);
    }

    @ExceptionHandler(NoResultException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNoResultException(Exception e) {
        logger.warn(e.getMessage());
        return "Record not found";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handlerException(Exception e) {
        logger.warn(e.getMessage(), e);
    }

}
