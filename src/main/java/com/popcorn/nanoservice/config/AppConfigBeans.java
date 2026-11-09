package com.popcorn.nanoservice.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.micrometer.tracing.Tracer;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class AppConfigBeans {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Bean
    public Gson jsonHelper() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.setPrettyPrinting().create();
    }

    @Bean
    @Profile("!prod")
    public CommonsRequestLoggingFilter commonsRequestLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeClientInfo(true);
        filter.setIncludeQueryString(true);
        filter.setIncludeHeaders(true);       // ⚠️ Mask sensitive headers if needed
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(100_000);
        filter.setBeforeMessagePrefix("➡️ Request [");
        filter.setBeforeMessageSuffix("]");
        filter.setAfterMessagePrefix("✅ Completed [");
        filter.setAfterMessageSuffix("]");
        return filter;
    }
}
