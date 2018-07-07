package com.zawhtetnaing.asartaline.zhn.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zawhtetnaing.asartaline.zhn.data.vos.WarDeeVO;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindData(WarDeeVO warDee);
}
