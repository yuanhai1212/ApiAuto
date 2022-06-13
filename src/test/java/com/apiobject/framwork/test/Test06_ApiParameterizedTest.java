package com.apiobject.framwork.test; /**
 * projectName: apiobject-framwork
 * fileName: Test06_ApiParameterizedTest.java
 * packageName: com.apiobject.test
 * date: 2021-06-19 下午5:41
 */



import com.apiobject.framwork.global.ApiLoader;
import com.apiobject.framwork.testcase.APITestCaseModel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: Test06_ApiParameterizedTest
 * @packageName: com.apiobject.test
 * @description:
 * @data: 2021-06-19 下午5:41
 **/
public class Test06_ApiParameterizedTest {
    public static final Logger logger = LoggerFactory.getLogger(Test06_ApiParameterizedTest.class);
    @ParameterizedTest(name = "{index}{1}")
    @MethodSource
    void apiTest(APITestCaseModel apiTestCaseModel, String name, String description){
        logger.info("【用例开始执行】");
        logger.info("用例名称："+name);
        logger.info("用例描述"+description);
        apiTestCaseModel.run();
    }

    static List<Arguments> apiTest(){
        ApiLoader.load("src/test/resources/api");
        List<Arguments> testcase = new ArrayList<>();
        String testCaseDir ="src/test/resources/testcase";

        Arrays.stream(new File(testCaseDir).list()).forEach(name->{
            String path =testCaseDir+"/"+name;
            try {
                APITestCaseModel apiTestCaseModel = APITestCaseModel.load(path);
                testcase.add(arguments(apiTestCaseModel,apiTestCaseModel.getName(),apiTestCaseModel.getDescription()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return testcase;
    }
}