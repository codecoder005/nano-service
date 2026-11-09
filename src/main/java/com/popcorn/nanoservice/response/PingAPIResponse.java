package com.popcorn.nanoservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response returned by the ping/health check endpoint")
public class PingAPIResponse {
    @Schema(description = "Current service status", example = "UP", allowableValues = {"UP", "DOWN"})
    private String status;

    @Schema(description = "Human-readable status message", example = "Service is healthy")
    private String message;

    @Schema(description = "Time the check was performed (ISO-8601)", example = "2026-11-09T22:46:00")
    private LocalDateTime timestamp;

    @Schema(description = "Optional extra details such as version or uptime",
            example = "{\"version\": \"1.0.0\", \"uptimeSeconds\": 3600}")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> additionalInfo;
}
