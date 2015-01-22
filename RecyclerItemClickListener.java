package org.pandacat.jt.pandacatbudget.Util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by jt on 1/22/15.
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    public static interface OnItemClickListener{
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }


    private OnItemClickListener mListener;
    private GestureDetector mGestureListener;

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener){
        mListener = listener;
        mGestureListener = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            public boolean onSingleTapUp(MotionEvent e){
                return true;
            }

            public void onLongPress(MotionEvent e){
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if(childView != null && mListener != null){
                    mListener.onItemLongClick(childView, recyclerView.getChildPosition(childView));
                }
            }
        });
    }

    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e){
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if(childView != null && mListener != null && mGestureListener.onTouchEvent(e)){
            mListener.onItemClick(childView, view.getChildPosition(childView));
        }
        return false;
    }
    //TODO put onTouchEvent later 3
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent){

    }
}
