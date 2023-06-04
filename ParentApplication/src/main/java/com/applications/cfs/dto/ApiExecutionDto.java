package com.applications.cfs.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor

public class ApiExecutionDto implements Serializable {
    private String nameofFeature;
    private Map<String,Object> jsonBody=new HashMap<>();
}
