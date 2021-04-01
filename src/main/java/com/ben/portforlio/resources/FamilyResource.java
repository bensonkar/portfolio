package com.ben.portforlio.resources;

import com.ben.portforlio.entities.Family;
import com.ben.portforlio.repositories.FamilyRepository;
import com.ben.portforlio.services.LogService;
import com.ben.portforlio.services.UsernameService;
import com.ben.portforlio.wrappers.FamilyWrapper;
import com.ben.portforlio.wrappers.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

/**
 * @author bkariuki
 */
@RestController
@RequestMapping("/family")
public class FamilyResource {
    protected final FamilyRepository familyRepository;
    protected final LogService logService;
    protected final UsernameService usernameService;

    public FamilyResource(FamilyRepository familyRepository, LogService logService, UsernameService usernameService) {
        this.familyRepository = familyRepository;
        this.logService = logService;
        this.usernameService = usernameService;
    }

    @PostConstruct
    public void create() {
        Family family = new Family();
        family.setAge(34);
        family.setEmail("admin@gmail.com");
        family.setFirstName("admin");
        family.setLastName("admin");
        family.setGender("male");
        family.setPhone("075468246");
        family.setRelationShip("brother");
        familyRepository.save(family);
    }

    @GetMapping("/all")
    public ResponseEntity family() {
        ResponseWrapper response = new ResponseWrapper();
        List data = familyRepository.findAll();
        response.setData(data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Family family) {
        ResponseWrapper response = new ResponseWrapper();
        Family email = familyRepository.findByEmail(family.getEmail());
        Family phone = familyRepository.findByPhone(family.getPhone());

        familyRepository.save(family);

        logService.log(Family.class.getSimpleName(),"CREATE",
                "creating record", usernameService.getUserId(), "Completed","record created successfully");
        response.setData(family);
        response.setCode(201);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody FamilyWrapper wrapper, @PathVariable Long id) {
        ResponseWrapper response = new ResponseWrapper();
        Family family = familyRepository.findById(id).get();

        if (family == null) {
            response.setCode(404);
            response.setMessage("provided id doesnt exist");
            logService.log(Family.class.getSimpleName(),"UPDATE",
                    "updating record", usernameService.getUserId(), "Failed",
                    "record not updated because it doesnt exists");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        family.setAge(wrapper.getAge());
        family.setEmail(wrapper.getEmail());
        family.setFirstName(wrapper.getFirstName());
        family.setLastName(wrapper.getLastName());
        family.setGender(wrapper.getGender());
        family.setPhone(wrapper.getPhone());
        family.setRelationShip(wrapper.getRelationShip());
        familyRepository.save(family);

        logService.log(Family.class.getSimpleName(),"UPDATE",
                "updating record", usernameService.getUserId(), "Completed","record updated successfully");

        response.setData(family);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        ResponseWrapper response = new ResponseWrapper();
        Family family = familyRepository.findById(id).get();

        if (family == null) {
            response.setCode(404);
            response.setMessage("provided id doesnt exist");
            logService.log(Family.class.getSimpleName(),"DELETE",
                    "deleting record", usernameService.getUserId(), "Failed",
                    "record not deleted because it doesnt exists");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        familyRepository.deleteById(family.getId());

        logService.log(Family.class.getSimpleName(),"DELETE",
                "deleting record", usernameService.getUserId(), "Completed","record deleted successfully");

        response.setData(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
