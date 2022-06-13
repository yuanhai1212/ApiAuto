package com.apiobject.framwork.api;

import com.apiobject.framwork.action.APiActionModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * 读取参数
 */
@Data
public class ApiObjectModel {
    private String name;
    private HashMap<String, APiActionModel> actions;
    private HashMap<String,String> obVariables=new HashMap<>();


    public static ApiObjectModel load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        ApiObjectModel reader = objectMapper.readValue(new File(path), ApiObjectModel.class);
        return reader;
    }
}
