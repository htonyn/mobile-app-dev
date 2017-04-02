package ponkberry.hoandemo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ponkberry.hoandemo.dialog.CustomDialog;

/**
 * Created by Ponk on 2/13/2017.
 */

public class DialogActivity extends BaseActivity {

    private int checkedID;
    public final int DIALOG = 12345;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DIALOG:
                    Bundle bundle = msg.getData();
                    String s = bundle.getString("msg");
                    toastShort("Dialog Message: "+s);
                    String content = msg.getData().getString("msg");
                    break;
                default:
            }
            super.handleMessage(msg);
        }
    };
    // Make sure to use the android.OS Handler

    @BindView(R.id.rdg) RadioGroup radioGroup;
    @OnClick(R.id.dialog_ok)
    public void onClick() {
        switch(checkedID) {
            case R.id.rb1:
                normalDialog();
                break;
            case R.id.rb2:
                listDialog();
                break;
            case R.id.rb3:
                singleChoiceDialog();
                break;
            case R.id.rb4:
                multiChoiceDialog();
                break;
            case R.id.rb5:
                waitingDialog();
                break;
            case R.id.rb6:
                progressDialog();
                break;
            case R.id.rb7:
                inputDialog();
                break;
            case R.id.rb8:
                customDialog();
                break;
            default:
        }
    }

    private void customDialog() {
        final CustomDialog dialog = new CustomDialog(this, new CustomDialog.ICustomDialogEventListener() {
            @Override
            public void onClickListener() {
//                dialog.dismiss();
                // You cannot place dialog here because dialog is currently being initialize in
                // this line, so you cannot use this to call to dismiss the dialog because
                // it does not reference anything currently
                //toastShort("OK Button was clicked.");

                Intent intent = new Intent();
                intent.putExtra("message","Dialog");
                setResult(RESULT_OK, intent);


                // If you want to finish an activity manually, use the below;
                finish();
            }
        });
//        CustomDialog dialog = new CustomDialog(this);
        dialog.setTitle("TestTitle");
        dialog.show();
    }

    private void inputDialog() {
        // Edit Text is a view.
        final EditText editText = new EditText(this);
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(this);
        inputDialog.setTitle("I'm an Input Dialog.").setView(editText);
        inputDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toastShort(editText.getText().toString());
            }
        });
        inputDialog.setNegativeButton("Cancel", null).show();
    }

    private void progressDialog() {
        final int MAX_PROGRESS = 100;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgress(0);
        progressDialog.setTitle("Downloading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.show();

        // This creates a new thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress < MAX_PROGRESS) {
                    try {
                        Thread.sleep(100);
                        progress++;
                        progressDialog.setProgress(progress);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //toastShort("Download Complete");
                Bundle bundle = new Bundle();
                bundle.putString("msg","Download succeeded.");
                Message msg = Message.obtain();
                msg.what = DIALOG;
                msg.setData(bundle);
                //mHandler.handleMessage(msg);
                mHandler.sendMessage(msg);
                progressDialog.cancel();
            }
        }).start();
    }

    ProgressDialog waitingDialog;
    private void waitingDialog() {
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle("I'm a waiting Dialog");
        waitingDialog.setMessage("Waiting...");
//        waitingDialog.setCancelable(false);
        waitingDialog.setCancelable(true);
        waitingDialog.show();
        // When the dialogue disappears from the screen, you use the dismiss listener.
        waitingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                toastShort("Dialog was cancelled!");
            }
        });

    }

    private void normalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("AlertTitle");
        builder.setMessage("This is a normal dialogue.");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                toastShort("You clicked Yes");
            }
        });
        builder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                toastShort("You clicked Neutral");
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                toastShort("You clicked Cancel");
            }
        });

        builder.show();
    }

    public void listDialog() {
        final String[] items = { "item1", "item2", "item3", "item4" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("I'm a List Dialog");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toastShort("You clicked: "+items[which]);
            }
        });
        builder.show();
    }

    int choice = 0;
    public void singleChoiceDialog() {
        final String[] items = { "item1", "item2", "item3", "item4" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("I'm a Single Choice Dialog");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setSingleChoiceItems(items, choice, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                choice = which;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toastShort("You clicked: "+choice);
            }
        });
        builder.show();
    }

    ArrayList<Integer> choices = new ArrayList<>();
    public void multiChoiceDialog() {
        final String[] items = { "item1", "item2", "item3", "item4" };
        final boolean initChoiceSets[]={false, false, false, false};
        choices.clear();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("I'm a multi-choice Dialog");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMultiChoiceItems(items, initChoiceSets, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    choices.add(which);
                } else {
                    choices.remove(which);
                }
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int size = choices.size();
                String str = "";
                for (int i = 0; i < size; i++) {
                    str += items[choices.get(i)] + " ";
                }
                toastShort("You chose: "+str);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                toastShort("You clicked Cancel");
            }
        });
                builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                toastShort("You checked the radio button"+checkedId);
                checkedID = checkedId;
            }
        });
    }

}
