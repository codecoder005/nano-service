package com.popcorn.nanoservice.controller;

import com.popcorn.nanoservice.response.PingAPIResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@Tag(
        name = "Ping API",
        description = """
        Quick liveness check for the service. \
        Call it to confirm the application is up, reachable and responding. \
        It is lightweight, needs no authentication, and touches no database or downstream service, \
        so it is safe for load balancers, uptime monitors and smoke tests to poll frequently.
        """
)
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class PingAPIController {
    @Operation(
            summary = "Check service health",
            description = """
            Lightweight liveness check. Returns `200 OK` when the service is up and able to handle requests. \
            It needs no authentication and makes no database or downstream calls, \
            so load balancers, uptime monitors and smoke tests can poll it frequently.
            """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Service is healthy",
                    content = @Content(schema = @Schema(implementation = PingAPIResponse.class))
            ),
            @ApiResponse(
                    responseCode = "503",
                    description = "Service is unavailable",
                    content = @Content(schema = @Schema(implementation = PingAPIResponse.class))
            )
    })
    @GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PingAPIResponse> ping(
            @Parameter(description = "Optional caller identifier, used for logging", example = "F123456")
            @RequestHeader(name = "client-id", required = false, defaultValue = "DEFAULT") final String clientId,

            @RequestHeader(name = HttpHeaders.CACHE_CONTROL, required = false, defaultValue = "no-cache") final String cacheControl
    ) {
        log.info("received a request from client-id: {}, with Cache-Control: {}", clientId, cacheControl);
        PingAPIResponse response = PingAPIResponse.builder()
                .status("ok")
                .message("pong")
                .timestamp(LocalDateTime.now())
                .additionalInfo(Map.of()).build();
        return ResponseEntity.status(HttpStatus.OK)
                .cacheControl(CacheControl.noCache())
                .body(response);
    }
}
