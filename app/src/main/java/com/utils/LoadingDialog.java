package com.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.main.functionlistsdemo.R;

/**
 * Created by pengfeili on 16/8/16.
 */

public class LoadingDialog {

    /**
     * 显示loadingDialog
     *
     * @param context
     * @return
     */
    public static MaterialDialog showLoadingDialog(Context context) {
        return showLoadingDialog(context, null);
    }

    /**
     * 显示 loadingDialog
     *
     * @param context
     * @param msg
     * @return
     */
    public static MaterialDialog showLoadingDialog(Context context, String msg) {
        MaterialDialog materialDialog = MaterialDialog.getBuilder(context)
                .customView(R.layout.dialog_loading, true)
                .cancelable(true)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                    }
                })
                .build();
        if (!TextUtils.isEmpty(msg)) {
            TextView textView = (TextView) materialDialog.getCustomView().findViewById(R.id.dialog_loading_desc);
            textView.setText(msg);
        }
        materialDialog.show();
        return materialDialog;
    }
}
