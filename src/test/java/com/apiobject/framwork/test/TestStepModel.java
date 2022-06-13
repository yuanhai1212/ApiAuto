package com.apiobject.framwork.test;

import com.apiobject.framwork.api.ApiObjectModel;
import com.apiobject.framwork.global.ApiLoader;
import com.apiobject.framwork.steps.AssertModel;
import com.apiobject.framwork.steps.StepModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestStepModel {
    public static final Logger logger= LoggerFactory.getLogger(TestStepModel.class);
    @BeforeAll
    static void loadTest(){
        ApiLoader.load("src/test/resources/api");

    }
    @Test
    void runTest() throws IOException {
        ArrayList actualParameter=new ArrayList();
        actualParameter.add("ww7a47a90c451e713a");
        actualParameter.add("Qi1Ofi1RTIs1p0tRi0pEGMFqd3yL7utSe9OV6DqQVUU");


        //assert
        ArrayList<AssertModel> asserts=new ArrayList();
        AssertModel assertModel=new AssertModel();
        assertModel.setActual("errcode");
        assertModel.setExpect("2");
        assertModel.setMatcher("equalTo");
        assertModel.setReason("getToken错误码校验01");
        asserts.add(assertModel);

        //save
        HashMap<String,String> save=new HashMap<>();
        save.put("accesstoken","access_token");

        HashMap<String,String> Glodsave=new HashMap<>();
        Glodsave.put("accesstoken","access_token");

        StepModel stepModel=new StepModel();
        stepModel.setApi("tokenhelper");
        stepModel.setAction("getToken");
        stepModel.setActualParameter(actualParameter);
        stepModel.setSave(save);
        stepModel.setAsserts(asserts);
        stepModel.setSaveGlobal(Glodsave);

        stepModel.run(null);
        logger.info("debugger");
    }


}
