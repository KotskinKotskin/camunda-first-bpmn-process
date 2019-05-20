package bpmn2.storm.backend

import bpmn2.storm.backend.model.LessonRequest
import junit.framework.Assert.assertEquals
import mu.KotlinLogging
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.camunda.bpm.engine.test.Deployment
import org.camunda.bpm.engine.test.ProcessEngineRule
import org.camunda.bpm.engine.test.util.CamundaBpmApiAwareTestCase
import org.camunda.bpm.engine.variable.Variables.putValue
import org.junit.Rule
import org.junit.Test
import org.springframework.core.io.ClassPathResource
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.RoundingMode
import java.text.DecimalFormat

private val logger = KotlinLogging.logger {}

class DecisionTests : CamundaBpmApiAwareTestCase() {

    @get:Rule
    val processEngineRule = ProcessEngineRule(ProcessTestConfig.processEngine)

    @Deployment(resources = [
        "dmn/firstCommunication.dmn"
    ])
    @Test
    fun testCoefficientCalculation() {
        var csvRecords = readInputParameter("testCoefficientCalculation.csv")
        var decisionTable = processEngineRule.decisionService.evaluateDecisionTableByKey("firstCommunication")
        for (item in csvRecords) {
            var lesson = LessonRequest().also {
                it.firstCommunication = item.get("in.lesson.firstCommunication").toBoolean()
                it.haveAgreement = item.get("in.lesson.haveAgreemnt").toBoolean()
                it.failedAssignation = item.get("in.lesson.failedAssignation").toBoolean()
            }
            var exceptedResult = item.get("out.Coefficient").toDouble()
            var variables = putValue("lesson", lesson)
            var result = decisionTable.variables(variables).evaluate().singleResult["Coef"]


            assertEquals(exceptedResult, result)

        }
    }

    @Deployment(resources = [
        "dmn/firstCommunication.dmn"
    ])
    @Test
    fun testCalculateWeight() {
        var csvRecords = readInputParameter("testCalculateWeight.csv")
        var decisionTable = processEngineRule.decisionService.evaluateDecisionTableByKey("calculateWeight")
        for (item in csvRecords) {
            var lesson = LessonRequest().also {
                it.firstCommunication = item.get("in.lesson.firstCommunication").toBoolean()
                it.haveAgreement = item.get("in.lesson.haveAgreemnt").toBoolean()
                it.failedAssignation = item.get("in.lesson.failedAssignation").toBoolean()
                it.recallCount = item.get("in.lesson.recallCount").toInt()
                it.nonAnswerCount = item.get("in.lesson.nonAnswerCount").toInt()
            }
            var exceptedResult1 = item.get("out.normalWeight")
            var exceptedResult2 = item.get("out.deadlineWeight")
            var variables = putValue("lesson", lesson)

            val df2 = DecimalFormat("##.##")
          //  df2.roundingMode = RoundingMode.HALF_DOWN

            var result1 = df2.format(decisionTable.variables(variables).evaluate().singleResult["normalWeight"] as Double)
            var result2 = df2.format(decisionTable.variables(variables).evaluate().singleResult["deadlineWeight"] as Double)



            assertEquals(exceptedResult1, result1)
            assertEquals(exceptedResult2, result2)

        }
    }


    @Deployment(resources = [
        "dmn/firstCommunication.dmn"
    ])
    @Test
    fun testCalculateTaskProps() {
        var csvRecords = readInputParameter("testCalculateTaskProps.csv")
        var decisionTable = processEngineRule.decisionService.evaluateDecisionTableByKey("CalculateTaskProps")

        for (item in csvRecords) {
            var lesson = LessonRequest().also {
                it.firstCommunication = item.get("in.lesson.firstCommunication").toBoolean()
                it.haveAgreement = item.get("in.lesson.haveAgreemnt").toBoolean()
                it.failedAssignation = item.get("in.lesson.failedAssignation").toBoolean()
                it.recallCount = item.get("in.lesson.recallCount").toInt()
                it.nonAnswerCount = item.get("in.lesson.nonAnswerCount").toInt()
                it.gradation = item.get("in.lesson.gradation")
            }
            var exceptedResult1 = item.get("out.type")
            var exceptedResult2 = item.get("out.poolName")
            var variables = putValue("lesson", lesson)

            var result1 = decisionTable.variables(variables).evaluate().singleResult["type"] as String
            var result2 = decisionTable.variables(variables).evaluate().singleResult["poolName"] as String



            assertEquals(exceptedResult1, result1)
            assertEquals(exceptedResult2, result2)


        }
    }


    @Deployment(resources = [
        "dmn/checkResult.dmn"
    ])
    @Test
    fun testCheckResult() {
        var csvRecords = readInputParameter("testCheckResult.csv")
        var decisionTable = processEngineRule.decisionService.evaluateDecisionTableByKey("checkResult")

        for (item in csvRecords) {
            var lesson = LessonRequest().also {
                it.recallCount = item.get("in.lesson.recallCount").toInt()
                it.nonAnswerCount = item.get("in.lesson.nonAnswerCount").toInt()

            }
            var taskResult = item.get("in.taskResult")

            var variables = putValue("lesson", lesson).putValue("taskResult", taskResult)

            var exceptedResult = item.get("out.nextOperation")
            var result = decisionTable.variables(variables).evaluate().singleResult["nextOperation"] as String

            assertEquals(exceptedResult, result)
        }
    }

    fun readInputParameter(csvName: String): CSVParser {
        return CSVParser(BufferedReader(
            InputStreamReader(ClassPathResource("testcases/$csvName").inputStream)), CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .withDelimiter(';')
            .withIgnoreHeaderCase()
            .withTrim())
    }
}
