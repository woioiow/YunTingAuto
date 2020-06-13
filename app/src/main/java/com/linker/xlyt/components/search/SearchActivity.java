package com.linker.xlyt.components.search;

import android.content.Intent;

import com.shinyv.cnr.BaseAutoActivity;
import com.shinyv.cnr.R;

/**
 * @Package: com.linker.xlyt.components.search
 * @Description:
 * @Author: Maclay
 * @Date: 2020/5/25 16:23
 */
public class SearchActivity extends BaseAutoActivity {

    int rid = R.drawable.bg_search;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        rid = R.drawable.bg_search_result;
        setBg(rid);
    }


    @Override
    public int getImageId() {
        return R.drawable.bg_search;
    }
}
