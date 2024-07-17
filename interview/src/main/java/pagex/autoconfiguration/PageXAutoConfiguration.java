package pagex.autoconfiguration;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pagex.advice.MyResponseAdvice;
import pagex.aop.PageXAop;

@Configuration
@Import( {PageXAop.class, MyResponseAdvice.class})
@ConditionalOnProperty(prefix = "pagex", value = "enable",havingValue="true",matchIfMissing=true)
public class PageXAutoConfiguration { //领头羊
}
