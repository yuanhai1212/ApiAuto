package com.apiobject.framwork.testcase;

import com.apiobject.framwork.api.ApiObjectModel;
import com.apiobject.framwork.steps.StepModel;
import com.apiobject.framwork.steps.StepResult;
import com.apiobject.framwork.test.TestAPiActionModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Data;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import until.FackUntil;
import until.FakerUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;

@Data
public class APITestCaseModel {
    public static final Logger logger= LoggerFactory.getLogger(APITestCaseModel.class);

    private String name;
    private String description;
    private ArrayList<StepModel> steps;
    private ArrayList<Executable> assertList=new ArrayList<>();
    private HashMap<String,String> testCaseVariables=new HashMap<>();


    public static APITestCaseModel load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        APITestCaseModel reader = objectMapper.readValue(new File(path), APITestCaseModel.class);
        return reader;
    }

    public void run(){
        this.testCaseVariables.put("getTimeStamp", FakerUtils.getTimeStamp());
        logger.info("用例变量更新"+testCaseVariables);
        /**
         * 遍历step变量
         */
        steps.forEach(step->{
            StepResult stepResult=step.run(testCaseVariables);
            /**
             * 处理step中的save
             */
            if(stepResult.getStepVariables().size()>0){
                testCaseVariables.putAll(stepResult.getStepVariables());
                logger.info("testcase变量更新"+testCaseVariables);
            }
            /**
             * 处理assertList进行断言
             */
            if(stepResult.getAssertList().size()>0){
                assertList.addAll(stepResult.getAssertList());

            }
            /**
             * 进行统一断言
             */
            assertAll("",assertList.stream());
        });

    }

}
