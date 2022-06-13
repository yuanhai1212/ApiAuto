package com.apiobject.framwork.global;

import com.apiobject.framwork.action.APiActionModel;
import com.apiobject.framwork.api.ApiObjectModel;
import com.apiobject.framwork.test.TestApiLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ApiLoader {
    public static final Logger logger= LoggerFactory.getLogger(ApiLoader.class);

    /**
     * 加载对象所有API object的对象，保存到本列表中
     */
    private static ArrayList<ApiObjectModel> apis=new ArrayList<>();

    public static void load(String dir){
        Arrays.stream(new File(dir).list()).forEach(path->{
            try {
                apis.add(ApiObjectModel.load(dir+"/"+path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static APiActionModel getAction(String apiname,String activeName){
        final APiActionModel[] aPiActionModel={new APiActionModel()};
        apis.stream().filter(api->api.getName().equals(apiname)).forEach(
                api ->{ aPiActionModel[0]=api.getActions().get(activeName);}
            );
        if(aPiActionModel[0]!=null){
            return aPiActionModel[0];
        }else{
            logger.info("没有找到接口对象"+apiname+"中的action："+activeName);
        }
        return null;

    }
}
