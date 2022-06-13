package com.apiobject.framwork.test;

import com.apiobject.framwork.global.ApiLoader;
import com.apiobject.framwork.steps.AssertModel;
import com.apiobject.framwork.steps.StepModel;
import com.apiobject.framwork.testcase.APITestCaseModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestApiTestXase {
    public static final Logger logger= LoggerFactory.getLogger(TestApiTestXase.class);
    @BeforeAll
    static void loadTest(){
        ApiLoader.load("src/test/resources/api");

    }
    @Test
    void runTest() throws IOException {
        APITestCaseModel apiTestCaseModel=APITestCaseModel.load("src/test/resources/createdepar.yml");
        logger.info("debgger");
    }


}
