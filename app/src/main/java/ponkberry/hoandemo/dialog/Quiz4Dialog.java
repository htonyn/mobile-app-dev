package ponkberry.hoandemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ponkberry.hoandemo.R;


/**
 * Created by Ponk on 3/7/2017.
 */

public class Quiz4Dialog extends Dialog {

    private Quiz4Dialog.ICustomDialogEventListener listener;
    private int checkedID;

    @BindView(R.id.quiz4_text)
    TextView tv;

    @BindView(R.id.rdg_quiz4)
    RadioGroup radioGroup;

    @OnClick(R.id.dialog_ok_quiz4)
    public void onClickOKRadio() {
        switch(checkedID) {
            case R.id.rb1_quiz4:
                listener.onToDialogListener();
                break;
            case R.id.rb2_quiz4:
                listener.onToListViewListener();
                break;
            default:
        }
        dismiss();
    }

    public Quiz4Dialog(@NonNull Context context, Quiz4Dialog.ICustomDialogEventListener listener) {
        super(context, R.style.dialog);
        this.listener = listener;
    }

    @OnClick(R.id.dialog_cancel_quiz4)
    public void onClickCancel() {
        listener.onCancelListener();
        dismiss();
    }

    public interface ICustomDialogEventListener{
        public void onCancelListener();
        public void onToDialogListener();
        public void onToListViewListener();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_quiz4);
        ButterKnife.bind(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                //toastShort("You checked the radio button"+checkedId);
                checkedID = checkedId;
            }
        });
    }
}
