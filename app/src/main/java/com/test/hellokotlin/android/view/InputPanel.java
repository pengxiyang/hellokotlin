package com.test.hellokotlin.android.view;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.hellokotlin.R;


public class InputPanel extends LinearLayout {

    private final static String TAG = "InputPanel";
    private LinearLayout mLyIput;
    private RelativeLayout inputBar;
    public static EditText textEditor;
    private ImageView emojiBtn;
    private ImageView sendBtn;


    private InputPanelListener listener;
    private SendListener sendListener;

    private int type;
    public static final int TYPE_TEXTMESSAGE = 1;
    public static final int TYPE_BARRAGE = 2;
    public static final int TYPE_NOTICE = 3;
    boolean flag;
    private Context mcontext;
     View layout;
    public void setType(int type) {
        this.type = type;
        if (textEditor != null){
            textEditor.setFilters(type == TYPE_BARRAGE ? new InputFilter[]{new LengthFilter(50)} : new InputFilter[0]);
            textEditor.requestFocus();
            inputBar.setSelected(true);
        }
    }

    public interface InputPanelListener {
        void onSendClick(String text, int msgType);
    }

    public interface SendListener {
        void onSend(String text, int msgType);
    }

    public InputPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        mcontext=context;
        initView();
    }


    private void initView() {
        layout = LayoutInflater.from(getContext()).inflate(R.layout.widget_input_panel, this);
        mLyIput=(LinearLayout) findViewById(R.id.ly_iput);
        inputBar = (RelativeLayout) findViewById(R.id.input_bar);
        textEditor = (EditText) findViewById(R.id.input_editor);
        emojiBtn = (ImageView) findViewById(R.id.input_emoji_btn);
        sendBtn = (ImageView) findViewById(R.id.input_send);
        textEditor.setFilters(type == TYPE_NOTICE ? new InputFilter[]{new LengthFilter(5)} : new InputFilter[0]);
        textEditor.setFilters(type == TYPE_BARRAGE ? new InputFilter[]{new LengthFilter(50)} : new InputFilter[0]);
        textEditor.setOnFocusChangeListener((v, hasFocus) -> {
            inputBar.setSelected(hasFocus);
        });
        textEditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sendBtn.setEnabled(!s.toString().isEmpty());
                int start = textEditor.getSelectionStart();
                int end = textEditor.getSelectionEnd();
                textEditor.removeTextChangedListener(this);
                textEditor.setText(s, TextView.BufferType.SPANNABLE);
                Log.e("TAG","输入框获取的文字"+s.toString());
                Log.e("TAG","输入框获取的文字2"+s);
                textEditor.setSelection(start, end);
                }
        });

        sendBtn.setOnClickListener(v -> {
            if (listener != null&& !TextUtils.isEmpty(textEditor.getText().toString())) {
                listener.onSendClick(textEditor.getText().toString(), type);
            }
            if (sendListener != null) {
                sendListener.onSend(textEditor.getText().toString(), type);
            }
            textEditor.getText().clear();
            //layout.setVisibility(GONE);
            mLyIput.setVisibility(GONE);//隐藏输入框框
            hideKeyboard();//关闭软键盘
            //QMUIKeyboardHelper.hideKeyboard(textEditor);
            //CommonUtils.hideInputMethod(getContext(), textEditor);
        });

    }

    public void setPanelListener(InputPanelListener l) {
        listener = l;
    }
    public void setSendListener(SendListener sendListener) {
        this.sendListener = sendListener;
    }


    /**
     * back键或者空白区域点击事件处理
     *
     * @return 已处理true, 否则false
     */
    public boolean onBackAction() {
        return false;
    }

    @Override
    public void setVisibility(int visibility) {
        Log.e("----visi", "setVisibility:= "+visibility );
        if (visibility == View.VISIBLE) {
            mLyIput.setVisibility(VISIBLE);
            textEditor.setFocusable(true);
            textEditor.setFocusableInTouchMode(true);
            textEditor.requestFocus();
        }else {
        }
        super.setVisibility(visibility);

    }



    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindowToken(), 0);
    }

}
