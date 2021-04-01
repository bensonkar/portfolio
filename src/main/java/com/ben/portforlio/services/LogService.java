package com.ben.portforlio.services;

import com.ben.portforlio.entities.AppLogs;
import com.ben.portforlio.repositories.AppLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bkariuki
 */
@Service
@Transactional
public class LogService {
    @Autowired
    protected AppLogsRepository appLogsRepository;

    public void log(String entity, String activity, String description, Long userId, String status, String notes) {
        AppLogs appLogs = new AppLogs();
        appLogs.setEntity(entity);
        appLogs.setActivity(activity);
        appLogs.setDescription(description);
        appLogs.setUserId(userId);
        appLogs.setStatus(status);
        appLogs.setNotes(notes);
        appLogsRepository.save(appLogs);
    }
}
