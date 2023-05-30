package com.applications.cfs.service;

import com.applications.cfs.dto.ApiExecutionDto;
import com.applications.cfs.exception.CfsException;
import com.applications.cfs.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import static com.applications.cfs.constants.AppConstants.DEMOFEATURE;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApiExecutionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExecutionService.class);
    public String execute(ApiExecutionDto apiExecutionDto) throws CfsException {

        LOGGER.info("******************************** execute Api in CFS service ******************************");
        String nameofFeature;
        String body;

        try {
            nameofFeature = JsonUtils.getObjectFromJsonString(apiExecutionDto.getNameofFeature(), String.class);
            body = JsonUtils.getObjectFromJsonString(apiExecutionDto.getBody(), String.class);

            if (nameofFeature == null || body == null)
                throw new RuntimeException();
        } catch (Exception e) {
            LOGGER.error("Exception while getting body or feature name", e);
            throw new CfsException(e.getMessage());
        }
        try {

            switch (nameofFeature) {
                case DEMOFEATURE:
                    return null; // mongodbReservedCU.reservedCUService(triggerCu,transData);

                default:
                    throw new CfsException("expected feature not found ");
            }
        } catch (CfsException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Exception while executing ApiExecution", e);
            throw new CfsException(e.getMessage());
        }
    }
}
