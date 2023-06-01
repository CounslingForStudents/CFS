package com.applications.cfs.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor

public class EmailDto {
    private Map<String,String> to =new HashMap<>();
    private Map<String,String> cc=new HashMap<>();
    private Map<String,String> bcc=new HashMap<>();
    private String subject;
    private String body;
}
