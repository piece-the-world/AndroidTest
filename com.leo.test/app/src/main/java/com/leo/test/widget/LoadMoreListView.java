package com.leo.test.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListView;

import com.leo.test.log.Logger;
import com.leo.test.log.Tags;

/**
 * Created by leo on 16-4-3.
 */
public class LoadMoreListView extends ListView implements
    GestureDetector.OnGestureListener {

  private LoadMoreListener loadMoreListener;

  public interface LoadMoreListener {
    void loadMore();
  }

  public LoadMoreListView(Context context) {
    super(context);
    init();
  }

  public LoadMoreListView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    gestureDetector = new GestureDetector(this.getContext(), this);
  }

  private GestureDetector gestureDetector;

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    if (gestureDetector.onTouchEvent(ev)) {
      return true;
    } else {
      return super.onTouchEvent(ev);
    }
  }

  @Override
  public boolean onDown(MotionEvent e) {
    return false;
  }

  @Override
  public void onShowPress(MotionEvent e) {

  }

  @Override
  public boolean onSingleTapUp(MotionEvent e) {
    return false;
  }

  @Override
  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
      float distanceY) {
    float direction = e2.getY() - e1.getY();
    Logger.debug(Tags.WIDGIT_LOADMORE, "MotionEvent1=%f,MotionEvent2=%f",
        e1.getY(), e2.getY());
    if (direction < 0 && isBottom()) {
      if (loadMoreListener != null) {
        loadMoreListener.loadMore();
      }
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void onLongPress(MotionEvent e) {

  }

  @Override
  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
      float velocityY) {
    return false;
  }

  private boolean isBottom() {
    Logger.debug(Tags.WIDGIT_LOADMORE, "getLastVisiblePosition=%d,getCount=%d",
        getLastVisiblePosition(), getCount());
    if (getLastVisiblePosition() == (getCount() - 1)) {
      Logger.debug(Tags.WIDGIT_LOADMORE,
          "BottomItemBottom=%d,ListViewHeight=%d",
          getChildAt(getLastVisiblePosition() - getFirstVisiblePosition())
              .getBottom(), getHeight());
      if (getChildAt(getLastVisiblePosition() - getFirstVisiblePosition())
          .getBottom() <= getHeight()) {
        return true;
      }
    }
    return false;

  }

  public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
    this.loadMoreListener = loadMoreListener;
  }

}
