package corbos.dvdapi.controllers;

import corbos.dvdapi.data.DVDDao;
import corbos.dvdapi.models.DVD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Makes our class injectable. It will be injected into Spring MVC core dependents.
//Tells Spring MVC to scan for methods that can handle HTTP requests.
//Tells Spring MVC to convert method results to JSON.
@RequestMapping("/api")
public class DVDController {

    private final DVDDao dao;

    public DVDController(DVDDao dao) {
        this.dao = dao;
    }

    @GetMapping("/dvds")
    public List<DVD> all() {
        return dao.getAll();
    }

    @PostMapping("/dvd")
    @ResponseStatus(HttpStatus.CREATED)   //sets the HTTP status code to 204 Created for all responses
    public DVD create(@RequestBody DVD dvd) {
        return dao.add(dvd);
    }

    @GetMapping("/dvd/{id}")
    public ResponseEntity<DVD> findById(@PathVariable int id){
        DVD res = dao.findById(id);
        if (res == null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/dvds/title/{title}")
    public ResponseEntity<DVD> findByTitle(@PathVariable String title){
        DVD res = dao.findByTitle(title);
        if (res == null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/dvds/year/{releaseYear}")
    public ResponseEntity<DVD> findByReleaseYear(@PathVariable int releaseYear){
        DVD res = dao.findByReleaseYear(releaseYear);
        if (res == null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/dvds/director/{directorName}")
    public ResponseEntity<DVD> findByDirectorName(@PathVariable String directorName){
        DVD res = dao.findByDirector(directorName);
        if (res == null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/dvds/rating/{rating}")
    public ResponseEntity<DVD> findByRating(@PathVariable String rating){
        DVD res = dao.findByRating(rating);
        if (res == null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(res);
    }

    @PutMapping("/dvd/{id}")
    public ResponseEntity updateById(@PathVariable int id, @RequestBody DVD dvd){
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if(id != dvd.getDvdid()){
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (dao.update(dvd)){
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @DeleteMapping("/dvd/{id}")
    public ResponseEntity deleteById(@PathVariable int id){
        if(dao.deleteById(id)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }



}
