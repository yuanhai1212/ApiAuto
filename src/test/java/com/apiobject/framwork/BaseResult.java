package com.apiobject.framwork;

import io.restassured.response.Response;
import lombok.Data;

@Data
public class BaseResult {
    private Response response;
}
