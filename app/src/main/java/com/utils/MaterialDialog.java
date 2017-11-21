package com.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by ynyang on 2015/9/30.
 */
public class MaterialDialog extends AlertDialog {

    private View mCustomView;

    public void setCustomView(View mCustomView) {
        this.mCustomView = mCustomView;
    }

    public static class ButtonCallback {

        //@Override
        public void onPositive(MaterialDialog dialog) {
        }

        //@Override
        public void onNegative(MaterialDialog dialog) {
        }

        //@Override
        public void onNeutral(MaterialDialog dialogInterface) {
        }
    }

    @Override
    public void show() {

        super.show();
        if (mCustomView != null) {
            setContentView(mCustomView);
        }
    }


    protected MaterialDialog(Context context) {
        super(context);
    }

    private Builder mBuilder;

    public MaterialDialog(Builder builder) {
        super(builder.getContext());
        this.mBuilder = builder;
    }

    public MaterialDialog customView(int layoutResId, boolean flag) {
        this.setContentView(layoutResId);
        return this;
    }

    public MaterialDialog negativeText(String text) {
        setButton(0, text, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return this;
    }

    public View getCustomView() {
        return mCustomView;
    }

    public static class Builder {
        protected Context context;
        protected int titleRes;
        protected int contentRes;
        protected String contentString;
        protected int positiveTextRes;
        protected int negativeTextRes;
        protected ButtonCallback callback;
        protected boolean cancelable;
        protected int contentColor, backgroundColor;
        protected OnClickListener positiveListener, negativeListener;
        private int customLayoutRes;
        private int neutralTextRes;
        private String titleStr;
        private boolean autoDismiss;

        public Builder(Context context) {
            this.context = context;
        }

        public Context getContext() {
            return this.context;
        }

        public Builder title(int resID) {
            this.titleRes = resID;
            return this;
        }

        public Builder title(String title) {
            this.titleStr = title;
            return this;
        }

        public Builder content(int resID) {
            this.contentRes = resID;
            return this;
        }

        public Builder content(String text) {
            this.contentString = text;
            return this;
        }

        public Builder positiveText(int resID) {
            this.positiveTextRes = resID;
            return this;
        }

        public Builder neutralText(int resID) {
            this.neutralTextRes = resID;
            return this;
        }

        public Builder callback(ButtonCallback buttonCallback) {
            this.callback = buttonCallback;
            return this;
        }

        public Builder negativeText(int resID) {
            this.negativeTextRes = resID;
            return this;
        }

        public Builder cancelable(boolean flag) {
            this.cancelable = flag;
            return this;
        }

        public Builder backgroundColor(int color) {
            this.backgroundColor = color;
            return this;
        }

        public Builder contentColor(int color) {
            this.contentColor = color;
            return this;
        }

        public Builder customView(int layoutId, boolean b) {
            this.customLayoutRes = layoutId;
            return this;
        }

        public MaterialDialog build() {
            MaterialDialog dialog = new MaterialDialog(this.getContext());
            init(dialog);
            return dialog;
        }

        public MaterialDialog show() {
            MaterialDialog dialog = build();
            dialog.show();
            return dialog;
        }

        private void init(MaterialDialog dialog) {
            final Builder builder = this;

            // Set cancelable flag and dialog background color
            dialog.setCancelable(builder.cancelable);
            dialog.setCanceledOnTouchOutside(builder.cancelable);

            if (!TextUtils.isEmpty(titleStr)) {
                dialog.setTitle(titleStr);
            } else {
                if (builder.titleRes != 0) {
                    dialog.setTitle(titleRes);
                } else {
                    dialog.setCustomTitle(null);
                }

                if (builder.contentRes != 0) {
                    dialog.setMessage(builder.getContext().getResources().getString(builder.contentRes));
                }
                if (!TextUtils.isEmpty(builder.contentString)) {
                    dialog.setMessage(builder.contentString);
                }
            }


            if (builder.positiveTextRes != 0) {
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, getContext().getResources().getString(builder.positiveTextRes), new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (callback != null) {
                            callback.onPositive((MaterialDialog) dialogInterface);
                        }

                        if (positiveListener != null) {
                            positiveListener.onClick(dialogInterface, i);
                        }
                    }
                });
            }

            if (builder.negativeTextRes != 0) {
                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, getContext().getResources().getString(builder.negativeTextRes), new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (callback != null) {
                            callback.onNegative((MaterialDialog) dialogInterface);
                        }

                        if (negativeListener != null) {
                            negativeListener.onClick(dialogInterface, i);
                        }
                    }
                });
            }

            if (builder.neutralTextRes != 0) {
                dialog.setButton(AlertDialog.BUTTON_NEUTRAL, getContext().getResources().getString(builder.negativeTextRes), new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (callback != null) {
                            callback.onNeutral((MaterialDialog) dialogInterface);
                        }

                        if (negativeListener != null) {
                            negativeListener.onClick(dialogInterface, i);
                        }
                    }
                });
            }


            if (customLayoutRes != 0) {
                LayoutInflater inflater = LayoutInflater.from(builder.context);
                final View customView = inflater.inflate(customLayoutRes, null, false);
                dialog.setCustomView(customView);

//                Should do this block after show()
//                dialog.setContentView(customView);
            }
        }


        public Builder dismissListener(OnDismissListener onDismissListener) {
            return this;
        }

        public Builder autoDismiss(boolean flag) {
            this.autoDismiss = flag;
            return this;
        }
    }

    public static MaterialDialog.Builder getBuilder(Context activity) {
        //初始化对话框
        MaterialDialog.Builder builder = new MaterialDialog.Builder(activity);
        builder.contentColor(activity.getResources().getColor(android.R.color.black))
                .backgroundColor(activity.getResources().getColor(android.R.color.white));
        return builder;
    }

}
