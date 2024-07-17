package pagex.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "pagex")
@EnableAutoConfiguration
public class PageXProperties {

    private boolean enable = true;
}
