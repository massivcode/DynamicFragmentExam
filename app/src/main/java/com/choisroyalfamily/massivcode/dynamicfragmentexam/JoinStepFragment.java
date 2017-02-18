package com.choisroyalfamily.massivcode.dynamicfragmentexam;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by massivcode@gmail.com on 2017. 2. 18. 10:16
 */
public class JoinStepFragment extends Fragment {

    // 0 : 아이디 입력, 1 : 핸드폰번호 입력, 2 : 인증번호 입력, 3 : 비밀번호 입력
    private int mStep;

    private JoinCallbackListener mListener;

    private EditText mEditText;

    public static JoinStepFragment newInstance(int step) {
        JoinStepFragment fragment = new JoinStepFragment();

        Bundle args = new Bundle();
        args.putInt("step", step);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (JoinCallbackListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mStep = bundle.getInt("step");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_join, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditText = (EditText) view.findViewById(R.id.join_et);
        Button endButton = (Button) view.findViewById(R.id.end_btn);
        ImageView nextImageView = (ImageView) view.findViewById(R.id.join_next_iv);

        if (mStep == 3) {
            endButton.setVisibility(View.VISIBLE);
            nextImageView.setVisibility(View.GONE);

            endButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String value = mEditText.getText().toString().trim();

                    if (TextUtils.isEmpty(value)) {
                        Toast.makeText(getContext(), "값을 입력하세요!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    mListener.onEndButtonClicked(value);
                }
            });
        } else {
            endButton.setVisibility(View.GONE);
            nextImageView.setVisibility(View.VISIBLE);
            nextImageView.setOnClickListener(mOnNextClickListener);
        }


        switch (mStep) {
            case 0:
                mEditText.setHint("사용할 아이디를 입력하세요!");
                break;
            case 1:
                mEditText.setHint("핸드폰 번호를 입력하세요!");
                break;
            case 2:
                mEditText.setHint("인증 번호를 입력하세요!");
                break;
            case 3:
                mEditText.setHint("비밀번호를 입력하세요!");
                break;
        }


    }

    private View.OnClickListener mOnNextClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String value = mEditText.getText().toString().trim();

            if (TextUtils.isEmpty(value)) {
                Toast.makeText(getContext(), "값을 입력하세요!", Toast.LENGTH_SHORT).show();
                return;
            }

            mListener.onNextClicked(mStep, value);
        }
    };
}
