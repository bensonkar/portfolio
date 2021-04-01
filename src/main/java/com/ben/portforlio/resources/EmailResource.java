package com.ben.portforlio.resources;

import com.ben.portforlio.entities.Family;
import com.ben.portforlio.repositories.FamilyRepository;
import com.ben.portforlio.services.EmailService;
import com.ben.portforlio.services.LogService;
import com.ben.portforlio.services.UsernameService;
import com.ben.portforlio.wrappers.EmailBody;
import com.ben.portforlio.wrappers.ResponseWrapper;
import com.ben.portforlio.wrappers.SendWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author bkariuki
 */
@RestController
@RequestMapping("/email")
public class EmailResource {
    protected final EmailService emailService;
    protected final FamilyRepository familyRepository;
    protected final LogService logService;
    protected final UsernameService usernameService;

    public EmailResource(EmailService emailService, FamilyRepository familyRepository, LogService logService,
                         UsernameService usernameService) {
        this.emailService = emailService;
        this.familyRepository = familyRepository;
        this.logService = logService;
        this.usernameService = usernameService;
    }

    @PostMapping("/send-to-one/{id}")
    public ResponseEntity sendToOne(@RequestBody EmailBody body, @PathVariable Long id) {
        ResponseWrapper response = new ResponseWrapper();
        Family family = familyRepository.findById(id).get();
        if (family == null) {
            response.setCode(404);
            response.setMessage("provided id doesnt exist");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        try {
            emailService.sendEmail(family.getEmail(), body.getTitle(), body.getMessage());
            logService.log(EmailBody.class.getSimpleName(), "SEND EMAIL",
                    "sending email", usernameService.getUserId(), "Completed", "email sent successfully");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HttpStatus.EXPECTATION_FAILED.value());
            response.setMessage("email send failed");

            logService.log(EmailBody.class.getSimpleName(), "SEND EMAIL",
                    "sending email", usernameService.getUserId(), "Failed", "email failed to send");
            return new ResponseEntity(response, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/send-to-all")
    public ResponseEntity sendToAll(@RequestBody EmailBody body) {
        ResponseWrapper response = new ResponseWrapper();
        List<Family> familyList = familyRepository.findAll();
        if (familyList.isEmpty()) {
            response.setCode(404);
            response.setMessage("no data available");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        try {
            familyList.parallelStream().forEach(family -> {
                emailService.sendEmail(family.getEmail(), body.getTitle(), body.getMessage());
                logService.log(EmailBody.class.getSimpleName(), "SEND EMAIL",
                        "sending email", usernameService.getUserId(), "Completed", "email sent successfully");
            });
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HttpStatus.EXPECTATION_FAILED.value());
            response.setMessage("email send failed");

            logService.log(EmailBody.class.getSimpleName(), "SEND EMAIL",
                    "sending email", usernameService.getUserId(), "Failed", "email failed to send");
            return new ResponseEntity(response, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/send-to-selected")
    public ResponseEntity sendToSelected(@RequestBody SendWrapper wrapper) {
        ResponseWrapper response = new ResponseWrapper();
        try {
            for (Long ids : wrapper.getIds()) {
                Family family = familyRepository.findById(ids).get();
                if (family == null) {
                    response.setCode(404);
                    response.setMessage("provided id doesnt exist " + ids);
                    return new ResponseEntity(response, HttpStatus.NOT_FOUND);
                }
                emailService.sendEmail(family.getEmail(), wrapper.getTitle(), wrapper.getMessage());
                logService.log(EmailBody.class.getSimpleName(), "SEND EMAIL",
                        "sending email", usernameService.getUserId(), "Completed", "email sent successfully");
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HttpStatus.EXPECTATION_FAILED.value());
            response.setMessage("email send failed");

            logService.log(EmailBody.class.getSimpleName(), "SEND EMAIL",
                    "sending email", usernameService.getUserId(), "Failed", "email failed to send");
            return new ResponseEntity(response, HttpStatus.EXPECTATION_FAILED);
        }
    }
}