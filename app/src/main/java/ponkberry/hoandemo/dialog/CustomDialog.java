package ponkberry.hoandemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ponkberry.hoandemo.R;

/**
 * Created by Ponk on 2/15/2017.
 */

public class CustomDialog extends Dialog {
    @OnClick(R.id.dialog_ok)
    public void onClick() {
        listener.onClickListener();
        dismiss();
    }

    private ICustomDialogEventListener listener;

    // The listener here is being intialized as a field so it can be used globally for the activity
    // By doing this, the CustomDialog will be able to call the dismiss for the current dialog
    // thus giving you a workaround to creating a dismiss function that you want to use while
    // maintaining the right order of action for the dismiss dialog using an alternate

    public interface ICustomDialogEventListener{
        public void onClickListener();
        //public void customDialogEvent();
    }

    public CustomDialog(@NonNull Context context, ICustomDialogEventListener listener) {
        super(context, R.style.dialog);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        ButterKnife.bind(this);
    }
}
