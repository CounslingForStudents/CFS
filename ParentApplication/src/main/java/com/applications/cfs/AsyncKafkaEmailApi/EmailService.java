package com.applications.cfs.AsyncKafkaEmailApi;

import com.applications.cfs.dto.AsyncKafkaEmailApiDto;

public interface EmailService {
    void sendEmail(AsyncKafkaEmailApiDto asyncKafkaEmailApiDto);
}
