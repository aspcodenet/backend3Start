package se.systementor.backend3start.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationPropertiesScan
@Configuration
@ConfigurationProperties(prefix = "integrations")
@Getter
@Setter
public class IntegrationProperties {
    private BlacklistProperties blacklist;
    private int tjena;
}

