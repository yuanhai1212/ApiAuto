package com.apiobject.framwork.steps;

import com.apiobject.framwork.global.ApiLoader;
import com.apiobject.framwork.global.GlobalVariables;
import com.apiobject.framwork.test.TestAPiActionModel;
import lombok.Data;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import until.PlaceholderUtils;

import io.restassured.response.*;
import java.util.ArrayList;
import java.util.HashMap;
@Data
public class StepModel {
    public static final Logger logger= LoggerFactory.getLogger(StepModel.class);

    /**
     * 需要定义AssertModel类
     */
    private String api;
    private String action;
    private ArrayList<String> actualParameter;
    private ArrayList<AssertModel> asserts;
    private HashMap<String,String> save;
    private HashMap<String ,String> saveGlobal;

    private ArrayList<String> finalActualParaeter=new ArrayList<>();
    private HashMap<String,String> stepVariables=new HashMap<>();
    private StepResult stepResult=new StepResult();
    private ArrayList<Executable> assertList=new ArrayList<>();

    public StepResult run(HashMap<String,String> testCaseVariables){
        /**
         * 需要定义AssertModel
         */

        if(actualParameter!=null){
            finalActualParaeter.addAll(PlaceholderUtils.resolveList(actualParameter,testCaseVariables));
        }
        /**
         * 根据case中配置的API对象和action信息，取出并执行action
         */
        Response response= ApiLoader.getAction(api,action).run(finalActualParaeter);
        /**
         * 存储save
         */
        if(save!=null){
            save.forEach((variablesName,path)->{
                String Value=response.path(path).toString();
                stepVariables.put(variablesName,Value);
                logger.info("step变量更新为"+stepVariables);
            });
        }

        if(saveGlobal!=null){
            saveGlobal.forEach((variablesName,path)->{
                String Value=response.path(path).toString();
                GlobalVariables.getGlobalVariable().put(variablesName,Value);
                logger.info("全局变量更新为"+GlobalVariables.getGlobalVariable());
            });
        }
        /**
         * 5、存储断言结果
         */
        if(asserts != null){
            asserts.stream().forEach(assertModel -> {
//                assertList.add(()->{assertThat(assertModel.getReason(),response.path(assertModel.getActual().toString()),equalTo(assertModel.getExpect()));});
            });
        }
        /**
         * 8、组装result
         */
        stepResult.setAssertList(assertList);
        stepResult.setStepVariables(stepVariables);
        stepResult.setResponse(response);
        return  stepResult;
    }
}
