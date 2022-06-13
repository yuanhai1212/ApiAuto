package com.apiobject.framwork.steps;

import io.restassured.response.Response;
import lombok.Data;

@Data
public class AssertModel {
    private String actual;
    private String  matcher;
    private String  expect;
    private String  reason;
    public void run(Response response){

    }
}
