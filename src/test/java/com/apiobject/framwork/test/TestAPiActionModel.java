package com.apiobject.framwork.test;

import com.apiobject.framwork.action.APiActionModel;
import com.apiobject.framwork.global.GlobalVariables;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;


public class TestAPiActionModel {
    public static final Logger logger= LoggerFactory.getLogger(TestAPiActionModel.class);

    @Test
    void RunTest(){
        ArrayList actualParameter=new ArrayList();
        actualParameter.add("ww7a47a90c451e713a");
        actualParameter.add("Qi1Ofi1RTIs1p0tRi0pEGMFqd3yL7utSe9OV6DqQVUU");

        APiActionModel aPiActionModel =new APiActionModel();
        aPiActionModel.setUrl("https://qyapi.weixin.qq.com/cgi-bin/${x}");
        HashMap<String,String> globalVariables=new HashMap<String,String>();

        globalVariables.put("x","gettoken");
        GlobalVariables.setGlobalVariable(globalVariables);
        ArrayList formalParameter=new ArrayList();

        formalParameter.add("corpid");
        formalParameter.add("corpsecret");
        aPiActionModel.setFormalParam(formalParameter);
        HashMap<String ,String> query=new HashMap<>();

        query.put("corpid","${corpid}");
        query.put("corpsecret","${corpsecret}");
        aPiActionModel.setQuery(query);

        Response response=aPiActionModel.run(actualParameter);
    }
}
