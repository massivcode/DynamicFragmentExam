package com.choisroyalfamily.massivcode.dynamicfragmentexam;

/**
 * Created by massivcode@gmail.com on 2017. 2. 18. 10:21
 */

public interface JoinCallbackListener {
    void onNextClicked(int currentStep, String value);

    void onEndButtonClicked(String value);
}
