package com.apiobject.framwork.test; /**
 * projectName: apiobject-framwork
 * fileName: Test07_HarToYamlTest.java
 * packageName: com.apiobject.test
 * date: 2021-06-19 下午5:56
 */



import com.apiobject.framwork.action.APiActionModel;
import com.apiobject.framwork.api.ApiObjectModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: Test07_HarToYamlTest
 * @packageName: com.apiobject.test
 * @description: 自动生成接口对象
 * @data: 2021-06-19 下午5:56
 **/
public class Test07_HarToYamlTest {
    public static final Logger logger = LoggerFactory.getLogger(Test07_HarToYamlTest.class);

    @Test
    void Test07_HarToYamlTest() throws HarReaderException, IOException {
        HarReader harReader = new HarReader();
        Har har = harReader.readFromFile(new File("src/test/resources/har/qyapi.weixin.qq.com.har"));
        logger.info("debugger!");

        ApiObjectModel apiObjectModel = new ApiObjectModel();
        APiActionModel apiActionModel = new APiActionModel();
        HashMap<String, APiActionModel> actions = new HashMap<>();
        HashMap<String, String> queryMap = new HashMap<>();

        har.getLog().getEntries().forEach(entrie->{
            HarRequest harRequest =  entrie.getRequest();
            harRequest.getQueryString().forEach(query ->{
                queryMap.put(query.getName(),query.getValue());
            });

            String method =harRequest.getMethod().toString();
            String url = harRequest.getUrl();

            apiActionModel.setQuery(queryMap);
            if(method.equals("GET")){
                apiActionModel.setGet(url);
            }else{
                apiActionModel.setPost(url);
            }
            actions.put(getRequestName(url),apiActionModel);
        });
        apiObjectModel.setName("tokenhelper");
        apiObjectModel.setActions(actions);
        ObjectMapper mapper =new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("src/test/resources/har/tokenhelper.yaml"),apiObjectModel);


    }
    public String getRequestName(String url) {
        String[] suburl = url.split("\\u003F")[0].split("/");
        String name = "";
        name = suburl[suburl.length - 1];
        return name;
    }
    @Test
    void harToYamlTest() throws IOException {
        ApiObjectModel apiObjectModel=ApiObjectModel.load("src/test/resources/har/tokenhelper.yaml");
        apiObjectModel.getActions().get("gettoken").run(null);
    }
}