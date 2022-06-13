package com.apiobject.framwork.global;

import java.util.HashMap;

public class GlobalVariables {
    static private HashMap<String,String > globalVariables=new HashMap<>();
    public static HashMap<String,String> getGlobalVariable(){
        return globalVariables;
    }

    public static void setGlobalVariable(HashMap<String,String > globalVariables){
        GlobalVariables.globalVariables=globalVariables;
    }
}
