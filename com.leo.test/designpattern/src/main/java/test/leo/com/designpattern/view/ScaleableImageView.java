package test.leo.com.designpattern.view;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

/**
 * Created by taochao on 16/6/6.
 */
public class ScaleableImageView extends ImageView {
  private ScaleGestureDetector scaleGestureDetector;
  private android.view.ScaleGestureDetector.OnScaleGestureListener listener;
  private Matrix matrix;
  private float focusX;
  private float focusY;
  private float transX;
  private float transY;
  private boolean scaleMode;

  public ScaleableImageView(Context context) {
    super(context);
    init();
  }

  public ScaleableImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public ScaleableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    matrix=new Matrix();
    setScaleType(ScaleType.MATRIX);
    listener= new ScaleGestureDetector.OnScaleGestureListener() {
      @Override
      public boolean onScale(ScaleGestureDetector detector) {
        float factor=detector.getScaleFactor();
//        Log.d("scale","factor="+factor);
        matrix.postScale(factor,factor);
        float dx = focusX * (1f - factor);
        float dy = focusY * (1f - factor);
        matrix.postTranslate(dx,dy);
//        Log.d("scale","matrix="+matrix.toString());
        setImageMatrix(matrix);
//        Log.d("scale","scaleMode=set");
        return true;
      }

      @Override
      public boolean onScaleBegin(ScaleGestureDetector detector) {
        focusX=detector.getFocusX();
        focusY=detector.getFocusY();
        return true;
      }

      @Override
      public void onScaleEnd(ScaleGestureDetector detector) {
      }
    };
    scaleGestureDetector=new ScaleGestureDetector(this.getContext(),listener);
  }
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    Log.d("scale","pointercount="+event.getPointerCount()+";actionM="+event.getActionMasked());
    scaleGestureDetector.onTouchEvent(event);
    switch (event.getActionMasked()){
      case MotionEvent.ACTION_DOWN:
        transX=event.getX();
        transY=event.getY();
        break;
      case MotionEvent.ACTION_POINTER_DOWN:
        scaleMode=true;
        break;
      case MotionEvent.ACTION_MOVE:
        if (!scaleMode){
          matrix.postTranslate(event.getX()-transX,event.getY()-transY);
          transX=event.getX();
          transY=event.getY();
          setImageMatrix(matrix);
        }
        break;
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_CANCEL:
        scaleMode=false;
        break;
    }
    return true;
  }
}
