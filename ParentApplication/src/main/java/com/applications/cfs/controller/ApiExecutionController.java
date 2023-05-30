package com.applications.cfs.controller;

import com.applications.cfs.CfsApplication;
import com.applications.cfs.constants.AppConstants;
import com.applications.cfs.exception.CfsException;
import com.applications.cfs.service.ApiExecutionService;
import com.applications.cfs.dto.ApiExecutionDto;
import com.applications.cfs.utils.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class ApiExecutionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExecutionController.class);

    @Autowired
    ApiExecutionService apiExecutionService;


    @PostMapping(value = "/execute", consumes="application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse execute(@RequestBody ApiExecutionDto apiExecutionDto) {


        try {
            String response = apiExecutionService.execute(apiExecutionDto);
            return new ApiResponse(HttpStatus.OK, AppConstants.SUCCESS, response);
        }
        catch(CfsException e){
            LOGGER.error("exception in executing ApiExecutionController {}",e.getMessage());
            return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

}
