package bpmn2.storm.backend

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.camunda.bpm.engine.runtime.ProcessInstance
import org.camunda.bpm.engine.test.Deployment
import org.camunda.bpm.engine.test.ProcessEngineRule
import org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*
import org.camunda.bpm.engine.test.util.CamundaBpmApiAwareTestCase
import org.camunda.bpm.extension.mockito.DelegateExpressions.autoMock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*
import org.junit.runner.RunWith
import org.springframework.core.io.ClassPathResource
import org.springframework.test.context.junit4.SpringRunner
import java.io.BufferedReader
import java.io.InputStreamReader


@RunWith(SpringRunner::class)
@Deployment(resources = ["bpmn/FirstLessonAssigne.bpmn",
    "dmn/checkResult.dmn",
    "dmn/firstCommunication.dmn"
])

class BankGuaranteeNotificationTest : CamundaBpmApiAwareTestCase() {
    @get:Rule
    val processEngineRule = ProcessEngineRule(ProcessTestConfig.processEngine)

    @Before

    fun setUp() {
        autoMock("bpmn/FirstLessonAssigne.bpmn")
    }


    @Deployment(resources = ["bpmn/FirstLessonAssigne.bpmn",
        "dmn/checkResult.dmn",
        "dmn/firstCommunication.dmn"
    ])
    @Test
    fun FinishResult() {

        var inputParameter = readInputParameters("processTestCases.csv","FinishResult")
        var taskResult = inputParameter.get("in.nextOperation")
        var checkResultResult =mapOf("nextOperation" to taskResult)

        var processInstance = startProcess(checkResultResult)

        assertThat(processInstance).isStarted.isWaitingAt("CaclauteTaskProps")
        execute(job())
        assertThat(processInstance).isStarted.isWaitingAt("AssigneeLesson")
        execute(job())
        assertThat(processInstance).isStarted.isWaitingAt("HandleResult")
        execute(job())
        assertThat(processInstance).isEnded.hasPassed("SucessFinish")

    }

    fun readInputParameters(csvName: String, testCaseName: String): CSVRecord {
        return CSVParser(BufferedReader(InputStreamReader(ClassPathResource("testcases/$csvName").inputStream)),
        CSVFormat.DEFAULT
        .withFirstRecordAsHeader()
        .withIgnoreHeaderCase()
                .withDelimiter(';')
                .withTrim()).first { it -> it.get("Name") == testCaseName }

    }

    fun startProcess(result: Map<String, String> ): ProcessInstance =
            runtimeService().startProcessInstanceByKey("FirstLessonAsignee", withVariables("checkResultResult", result)
 )

}
