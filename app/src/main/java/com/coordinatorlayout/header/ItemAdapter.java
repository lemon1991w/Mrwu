package com.coordinatorlayout.header;

import android.content.Context;
import android.widget.TextView;

import com.main.functionlistsdemo.R;

import java.util.List;

/**
 * @ explain:
 * @ author： 2016/10/25 16:42
 *
 */
public class ItemAdapter extends BaseRecyclerAdapter<String> {

    public ItemAdapter(Context context, List<String> datas) {
        super(context, R.layout.item_string, datas);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, String item, int position) {
        TextView tv=holder.getView(R.id.tv);
        tv.setText(item);
    }
}
