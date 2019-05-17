package bpmn2.storm.backend.configuration

import bpmn2.storm.backend.delegate.PreDecisionDelegate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
public class BeanConfig {
    @Bean
    fun preDecisionDeleagate(): PreDecisionDelegate {

        return PreDecisionDelegate()
    }
}