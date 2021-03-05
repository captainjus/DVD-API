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
    public List<DVD> findByTitle(@PathVariable String title){
       // List<DVD> res = dao.findByTitle(title);

        return dao.findByTitle(title);
    }

    @GetMapping("/dvds/year/{releaseYear}")
    public List<DVD> findByReleaseYear(@PathVariable int releaseYear){

        return dao.findByReleaseYear(releaseYear);
    }

    @GetMapping("/dvds/director/{directorName}")
    public List<DVD> findByDirectorName(@PathVariable String directorName){

        return dao.findByDirector(directorName);
    }

    @GetMapping("/dvds/rating/{rating}")
    public List<DVD> findByRating(@PathVariable String rating){
        return dao.findByRating(rating);
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
