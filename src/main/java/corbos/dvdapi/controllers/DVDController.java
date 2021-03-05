package corbos.dvdapi.controllers;

import corbos.dvdapi.data.DVDDao;
import corbos.dvdapi.models.DVD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Makes our class injectable. It will be injected into Spring MVC core dependents.
//Tells Spring MVC to scan for methods that can handle HTTP requests.
//Tells Spring MVC to convert method results to JSON.
@RequestMapping("/api/dvd")
public class DVDController {

    private final DVDDao dao;

    public DVDController(DVDDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<DVD> all() {
        return dao.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)   //sets the HTTP status code to 204 Created for all responses
    public DVD create(@RequestBody DVD dvd) {
        return dao.add(dvd);
    }
    
}
