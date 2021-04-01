package com.ben.portforlio.resources;

import com.ben.portforlio.entities.AppLogs;
import com.ben.portforlio.repositories.AppLogsRepository;
import com.ben.portforlio.wrappers.ResponseWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author bkariuki
 */
@RestController
@RequestMapping("/app-logs")
public class AppLogsResource {

    protected final AppLogsRepository appLogsRepository;

    public AppLogsResource(AppLogsRepository appLogsRepository) {
        this.appLogsRepository = appLogsRepository;
    }

    @GetMapping("/logs")
    public ResponseEntity sessionLogs() {
        ResponseWrapper response = new ResponseWrapper();
        List<AppLogs> logs = appLogsRepository.findAll();
        response.setData(logs);
        return ResponseEntity.ok(response);
    }
}
