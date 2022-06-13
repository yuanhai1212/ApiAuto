package com.apiobject.framwork.test;

import com.apiobject.framwork.action.APiActionModel;
import com.apiobject.framwork.api.ApiObjectModel;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

public class TestAPIObjectModel {
    public static final Logger logger= LoggerFactory.getLogger(TestAPIObjectModel.class);

    @Test
    void loadTest() throws IOException {
        ArrayList actualParameter=new ArrayList();
        actualParameter.add("ww7a47a90c451e713a");
        actualParameter.add("Qi1Ofi1RTIs1p0tRi0pEGMFqd3yL7utSe9OV6DqQVUU");
        ApiObjectModel apiObjectModel = new ApiObjectModel().load("src/test/resources/api/getToken.yml");
        apiObjectModel.getActions().get("getToken").run(actualParameter);
    }


}
