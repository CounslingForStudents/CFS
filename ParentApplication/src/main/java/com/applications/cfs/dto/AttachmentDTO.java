package com.applications.cfs.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AttachmentDTO implements Serializable {
    private String AttachmentFileUrl;
    private String AttachmentFileName;
}
