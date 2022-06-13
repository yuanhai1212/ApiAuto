package com.apiobject.framwork.action;

import com.apiobject.framwork.global.GlobalVariables;
import io.restassured.response.*;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import until.PlaceholderUtils;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;


@Data
public class APiActionModel {
    private String method="get";
    private String url;
    private String body;
    private String contentType;
    private HashMap<String ,String > query;
    private HashMap<String,String> headers;
    private String post;
    private String get;
    private Response response;
    private ArrayList<String> formalParam;
    private HashMap<String,String> actionVariables = new HashMap<>();


    public Response run(ArrayList<String> actualParemter){
        String runUrl =this.url;
        String runBody = this.body;
        HashMap<String,String> finalQuery = new HashMap<>();
        /**
         * 确定请求方法
         */
        if(post!=null){
            url=post;
            method="post";
        }else if(get!=null){
            url=get;
            method="get";
        }
        /**
         * url请求参数全局变量替换
         * 需要编写占位符工具
         */
        if(query!=null){
            finalQuery.putAll(PlaceholderUtils.resolveMap(query, GlobalVariables.getGlobalVariable()));
        }
        runBody=PlaceholderUtils.resolveString(body,GlobalVariables.getGlobalVariable());
        runUrl=PlaceholderUtils.resolveString(url,GlobalVariables.getGlobalVariable());
        if(formalParam!=null && actualParemter !=null && formalParam.size() >0 && actualParemter.size() !=0){
            for (int i = 0; i <formalParam.size() ; i++) {
                actionVariables.put(formalParam.get(i),actualParemter.get(i));
            }
            if(query !=null){
                finalQuery.putAll(PlaceholderUtils.resolveMap(query,actionVariables));
            }
            //body全局变量替换
            runBody = PlaceholderUtils.resolveString(runBody, actionVariables);
            //url全局变量替换
            runUrl = PlaceholderUtils.resolveString(runUrl,actionVariables);

        }
        RequestSpecification requestSpecification = given().log().all();
        if(headers !=null){
            requestSpecification.headers(headers);
        }
        if(contentType !=null){
            requestSpecification.contentType(contentType);
        }
        if(finalQuery !=null && finalQuery.size()>0){
            requestSpecification.formParams(finalQuery);
        }else if(runBody!=null){
            requestSpecification.body(runBody);
        }
        Response response=requestSpecification.request(method,runUrl).then().log().all().extract().response();
        this.response=response;
        return response;
    }

}
