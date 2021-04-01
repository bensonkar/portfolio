package com.ben.portforlio.resources;

import com.ben.portforlio.entities.RelationShip;
import com.ben.portforlio.repositories.AppLogsRepository;
import com.ben.portforlio.repositories.FamilyRepository;
import com.ben.portforlio.repositories.RelationshipRepository;
import com.ben.portforlio.repositories.SystemUserRepository;
import com.ben.portforlio.utils.DashboardCharts;
import com.ben.portforlio.utils.Extra;
import com.ben.portforlio.wrappers.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bkariuki
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardResource {

    @Autowired
    FamilyRepository familyRepository;
    @Autowired
    SystemUserRepository userRepository;
    @Autowired
    RelationshipRepository relationshipRepository;
    @Autowired
    AppLogsRepository logsRepository;


    @GetMapping("/graph")
    public ResponseEntity graph() {
        ResponseWrapper response = new ResponseWrapper();
        long family = familyRepository.count();
        long relation = relationshipRepository.count();
        long reports = logsRepository.count();
        List<DashboardCharts> dashboardChartsList = new ArrayList<>();
        dashboardChartsList.add(new DashboardCharts("family members", family, new Extra("family members")));
        dashboardChartsList.add(new DashboardCharts("relationship total", relation, new Extra("relationship total")));
        dashboardChartsList.add(new DashboardCharts("reports total", reports, new Extra("reports total")));

        response.setData(dashboardChartsList);
        return ResponseEntity.ok(response);
    }
}
