package com.apiobject.framwork.test;

import com.apiobject.framwork.global.ApiLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.ArrayList;

public class TestApiLoader {
    public static final Logger logger= LoggerFactory.getLogger(TestApiLoader.class);
    @BeforeEach
    public void loadersTest() throws IOException {
        ApiLoader.load("src/test/resources/api/");
        logger.info("debugger");
    }
    @Test
    void ActionTest(){
        ArrayList actualParameter=new ArrayList();
        actualParameter.add("ww7a47a90c451e713a");
        actualParameter.add("Qi1Ofi1RTIs1p0tRi0pEGMFqd3yL7utSe9OV6DqQVUU");
        ApiLoader.getAction("tokenhelper","getToken").run(actualParameter);
    }
}
