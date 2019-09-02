package io.github.djunicode.canteenapp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

// if list view is inside a recycler view
// wrap content on its height doesn't work
//hence we need to create a custom listview and override onMeasure
// https://stackoverflow.com/a/31674843

public class MyListView  extends ListView {

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
