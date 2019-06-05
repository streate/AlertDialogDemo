package com.fd.alertdialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static String TAG = MainActivity.class.getSimpleName();
    private int chedkedItem = 0;
    private String name;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();
    }

    private void bindView() {
        Button btn_normal_dialog = (Button) findViewById(R.id.btn_normal_dialog);
        Button btn_item_dialog = (Button) findViewById(R.id.btn_item_dialog);
        Button btn_single_choice = (Button) findViewById(R.id.btn_single_choice);
        Button btn_multi_choice = (Button) findViewById(R.id.btn_multi_choice);
        Button btn_custom_dialog = (Button) findViewById(R.id.btn_custom_dialog);
        Button btn_modity_display = (Button) findViewById(R.id.btn_modity_display);
        btn_normal_dialog.setOnClickListener(this);
        btn_item_dialog.setOnClickListener(this);
        btn_single_choice.setOnClickListener(this);
        btn_multi_choice.setOnClickListener(this);
        btn_custom_dialog.setOnClickListener(this);
        btn_modity_display.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_normal_dialog:
                tipDialog();                //提示对话框
                break;
            case R.id.btn_item_dialog:
                itemListDialog();           //列表对话框
                break;
            case R.id.btn_single_choice:
                singleChoiceDialog();       //单选对话框
                break;
            case R.id.btn_multi_choice:
                multiChoiceDialog();        //多选对话框
                break;
            case R.id.btn_custom_dialog:
                customDialog();             //自定义对话框
                break;
            case R.id.btn_modity_display:
                customDialogDisplay();
                break;
            default:
                break;
        }
    }

    /**
     * 提示对话框
     */
    public void tipDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("提示：");
        builder.setMessage("这是一个普通对话框，");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(true);            //点击对话框以外的区域是否让对话框消失

        //设置正面按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "你点击了确定", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //设置反面按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "你点击了取消", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //设置中立按钮
        builder.setNeutralButton("保密", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "你选择了中立", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


        AlertDialog dialog = builder.create();      //创建AlertDialog对象
        //对话框显示的监听事件
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Log.e(TAG, "对话框显示了");
            }
        });
        //对话框消失的监听事件
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.e(TAG, "对话框消失了");
            }
        });
        dialog.show();                              //显示对话框
    }

    /**
     * 列表对话框
     */
    private void itemListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("选择你喜欢的课程：");
        builder.setCancelable(true);
        final String[] lesson = new String[]{"语文", "数学", "英语", "化学", "生物", "物理", "体育"};
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setIcon(R.mipmap.tab_better_pressed)
                .setItems(lesson, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "你选择了" + lesson[which], Toast.LENGTH_SHORT).show();
                    }
                }).create();
        //设置正面按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //设置反面按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();     //创建AlertDialog对象
        dialog.show();                              //显示对话框
    }


    /**
     * 单选对话框
     */
    public void singleChoiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("你现在居住地是：");
        final String[] cities = {"北京", "上海", "广州", "深圳", "杭州", "天津", "成都"};

        builder.setSingleChoiceItems(cities, chedkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "你选择了" + cities[which], Toast.LENGTH_SHORT).show();
                chedkedItem = which;
            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();  //创建AlertDialog对象
        dialog.show();                           //显示对话框
    }

    /**
     * 复选对话框
     */
    public void multiChoiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("请选择你喜欢的颜色：");
        final String[] colors = {"红色", "橙色", "黄色", "绿色", "蓝色", "靛色", "紫色"};
        final List<String> myColors = new ArrayList<>();

        builder.setMultiChoiceItems(colors, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    myColors.add(colors[which]);
                } else {
                    myColors.remove(colors[which]);
                }
            }
        });

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String result = "";
                for (String color : myColors) {
                    result += color + "、";
                }
                Toast.makeText(getApplicationContext(), "你选择了: " + result, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myColors.clear();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();      //创建AlertDialog对象
        dialog.show();                               //显示对话框
    }


    /**
     * 自定义登录对话框
     */
    public void customDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(MainActivity.this, R.layout.activity_custom, null);
        dialog.setView(dialogView);
        dialog.show();

        final EditText et_name = dialogView.findViewById(R.id.et_name);
        final EditText et_pwd = dialogView.findViewById(R.id.et_pwd);

        final Button btn_login = dialogView.findViewById(R.id.btn_login);
        final Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = et_name.getText().toString();
                pwd = et_pwd.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(MainActivity.this, "用户名或密码不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this, "用户名：" + name + "\n" + "用户密码：" + pwd, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    /**
     * 自定义登录对话框
     */
    public void customDialogDisplay() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(MainActivity.this, R.layout.activity_layout, null);
        dialog.setView(dialogView);
        dialog.show();
        dialog.getWindow().setLayout(ScreenUtils.getScreenWidth(this) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT);
        setScreenBgLight();
        final EditText et_name = dialogView.findViewById(R.id.et_name);
        final EditText et_pwd = dialogView.findViewById(R.id.et_pwd);

        final Button btn_login = dialogView.findViewById(R.id.btn_login);
        final Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = et_name.getText().toString();
                pwd = et_pwd.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(MainActivity.this, "用户名或密码不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this, "用户名：" + name + "\n" + "用户密码：" + pwd, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        popFromBottom(dialog);
    }


    private void popFromBottom(Dialog dialog) {
        Window win = dialog.getWindow();
        win.setGravity(Gravity.BOTTOM);   // 这里控制弹出的位置
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setBackgroundDrawable(null);
        win.setAttributes(lp);
    }

    // 设置屏幕背景变暗
    private void setScreenBgDarken() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        lp.dimAmount = 0.5f;
        getWindow().setAttributes(lp);
    }

    // 设置屏幕背景变亮
    private void setScreenBgLight() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1.0f;
        lp.dimAmount = 1.0f;
        getWindow().setAttributes(lp);
    }


}
