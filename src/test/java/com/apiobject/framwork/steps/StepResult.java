package com.apiobject.framwork.steps;
import com.apiobject.framwork.BaseResult;
import lombok.Data;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.HashMap;


@Data
public class StepResult extends BaseResult {

    private ArrayList<Executable> assertList;
    private HashMap<String,String> stepVariables=new HashMap<>();

}
