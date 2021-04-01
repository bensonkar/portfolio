package com.ben.portforlio.resources;

import com.ben.portforlio.entities.Family;
import com.ben.portforlio.entities.RelationShip;
import com.ben.portforlio.repositories.RelationshipRepository;
import com.ben.portforlio.services.LogService;
import com.ben.portforlio.services.UsernameService;
import com.ben.portforlio.wrappers.FamilyWrapper;
import com.ben.portforlio.wrappers.RelationWrapper;
import com.ben.portforlio.wrappers.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author bkariuki
 */
@RestController
@RequestMapping("/relation")
public class RelationResource {
    protected final RelationshipRepository relationshipRepository;
    protected final LogService logService;
    protected final UsernameService usernameService;

    public RelationResource(RelationshipRepository relationshipRepository, LogService logService, UsernameService usernameService) {
        this.relationshipRepository = relationshipRepository;
        this.logService = logService;
        this.usernameService = usernameService;
    }

    @PostConstruct
    public void init() {
        RelationShip relationShip = new RelationShip("father");
        relationshipRepository.save(relationShip);
        relationshipRepository.save(new RelationShip("mother"));
        relationshipRepository.save(new RelationShip("daughter"));
        relationshipRepository.save(new RelationShip("son"));
        relationshipRepository.save(new RelationShip("grandfather"));
        relationshipRepository.save(new RelationShip("grandmother"));
        relationshipRepository.save(new RelationShip("uncle"));
        relationshipRepository.save(new RelationShip("aunt"));
    }

    @GetMapping("/all")
    public ResponseEntity all() {
        ResponseWrapper response = new ResponseWrapper();
        List data = relationshipRepository.findAll();
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody RelationShip relation) {
        ResponseWrapper response = new ResponseWrapper();
        RelationShip relationShip = relationshipRepository.findByName(relation.getName());
        if (relationShip != null) {
            response.setCode(409);
            response.setMessage("Relation name already exists");
            logService.log(RelationShip.class.getSimpleName(), "CREATE",

                    "creating record", usernameService.getUserId(), "Failed",
                    "record not created because it exists");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
        relationshipRepository.save(relation);
        logService.log(RelationShip.class.getSimpleName(), "CREATE",
                "creating record", usernameService.getUserId(), "Completed", "record created successfully");
        response.setData(relation);
        response.setCode(201);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody RelationWrapper wrapper, @PathVariable Long id) {
        ResponseWrapper response = new ResponseWrapper();
        RelationShip relationShip = relationshipRepository.findById(id).get();
        RelationShip relation= relationshipRepository.findByName(wrapper.getName());

        if (relationShip == null) {
            response.setCode(404);
            response.setMessage("provided id doesnt exist");
            logService.log(RelationShip.class.getSimpleName(), "UPDATE",
                    "updating record", usernameService.getUserId(), "Failed",
                    "record not updated because record not found");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        if (relation != null) {
            response.setCode(409);
            response.setMessage("Relation name already exists");
            logService.log(RelationShip.class.getSimpleName(), "UPDATE",
                    "updating record", usernameService.getUserId(),
                    "Failed", "record not updated because already exists");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }

        relationShip.setName(wrapper.getName());

        relationshipRepository.save(relationShip);
        logService.log(RelationShip.class.getSimpleName(), "UPDATE",
                "updating record", usernameService.getUserId(), "Completed", "record updated successfully");

        response.setData(wrapper);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        ResponseWrapper response = new ResponseWrapper();
        RelationShip relationShip = relationshipRepository.findById(id).get();

        if (relationShip == null) {
            response.setCode(404);
            response.setMessage("provided id doesnt exist");
            logService.log(RelationShip.class.getSimpleName(), "DELETE",
                    "deleting record", usernameService.getUserId(), "Completed",
                    "record not deleted because it does not exist ");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        relationshipRepository.deleteById(relationShip.getId());

        logService.log(RelationShip.class.getSimpleName(), "DELETE",
                "deleting record", usernameService.getUserId(), "Completed", "record deleted successfully");

        response.setData(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
