package bpmn2.storm.backend

import org.camunda.bpm.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration

class ProcessTestConfig {
    companion object {
        val processEngine = StandaloneInMemProcessEngineConfiguration
            .createStandaloneInMemProcessEngineConfiguration()
            .buildProcessEngine()
    }
}
