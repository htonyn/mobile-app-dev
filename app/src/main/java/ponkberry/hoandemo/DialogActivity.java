package ponkberry.hoandemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.widget.RadioGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ponk on 2/13/2017.
 */

public class DialogActivity extends BaseActivity {

    private int checkedID;

    @BindView(R.id.rdg) RadioGroup radioGroup;
    @OnClick(R.id.dialogue_ok)
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
                break;
            case R.id.rb5:
                break;
            case R.id.rb6:
                break;
            case R.id.rb7:
                break;
            case R.id.rb8:
                break;
            default:
        }
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
                builder.show();
    }

    public void showDialog() {}


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
