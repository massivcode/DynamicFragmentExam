package com.choisroyalfamily.massivcode.dynamicfragmentexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JoinCallbackListener {

    private int mCurrentStep;
    private List<String> mValue = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        addFragment(0);
    }

    private void addFragment(int step) {
        mCurrentStep = step;

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, JoinStepFragment.newInstance(mCurrentStep))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        int fragmentBackStackCount = getSupportFragmentManager().getBackStackEntryCount();

        if (fragmentBackStackCount == 1) {
            finish();
        } else {
            mCurrentStep--;
            mValue.remove(mCurrentStep);
            getSupportFragmentManager().popBackStackImmediate();
        }

    }

    @Override
    public void onNextClicked(int currentStep, String value) {
        mValue.add(value);
        addFragment(currentStep + 1);
    }

    @Override
    public void onEndButtonClicked(String value) {
        mValue.add(value);
        Toast.makeText(this, mValue.toString(), Toast.LENGTH_SHORT).show();
    }
}
