package test.leo.com.designpattern.Structual.Decorator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by leo on 16-2-24.
 */
public class GraphView extends View {
  private Paint mPaint;
  private int width;
  private int height;
  private Rect rect1;
  private Rect rect2;
  private Rect rect3;
  private Rect rect4;
  private Path path;

  public GraphView(Context context) {
    super(context);
    init();
  }

  public GraphView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public GraphView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    rect1 = new Rect();
    rect2 = new Rect();
    rect3 = new Rect();
    rect4 = new Rect();
    path = new Path();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    doCompute();
    mPaint.setColor(Color.GRAY);
    canvas.drawRect(rect1, mPaint);
    canvas.drawRect(rect2, mPaint);
    canvas.drawRect(rect3, mPaint);
    canvas.drawRect(rect4, mPaint);
    mPaint.setColor(Color.BLUE);
    mPaint.setTextSize(metrologyWidth(0.04f));
    canvas.drawText("Component", metrologyWidth(0.38f), metrologyHeight(0.12f),
        mPaint);
    canvas.drawText("ConcreteComponent", metrologyWidth(0.02f),
        metrologyHeight(0.47f), mPaint);
    canvas.drawText("Decorator", metrologyWidth(0.63f), metrologyHeight(0.47f),
        mPaint);
    canvas.drawText("ConcreteDecorator", metrologyWidth(0.57f),
        metrologyHeight(0.82f), mPaint);
    mPaint.setColor(Color.BLACK);
    mPaint.setStyle(Paint.Style.STROKE);
    canvas.drawPath(path, mPaint);
  }

  private void doCompute() {
    width = getWidth();
    height = getHeight();
    rect1.set((int) metrologyWidth(0.32f), (int) metrologyHeight(0.04f),
        (int) metrologyWidth(0.69f), (int) metrologyHeight(0.20f));
    rect2.set((int) metrologyWidth(0.02f), (int) metrologyHeight(0.39f),
        (int) metrologyWidth(0.40f), (int) metrologyHeight(0.53f));
    rect3.set((int) metrologyWidth(0.56f), (int) metrologyHeight(0.39f),
        (int) metrologyWidth(0.96f), (int) metrologyHeight(0.53f));
    rect4.set((int) metrologyWidth(0.56f), (int) metrologyHeight(0.76f),
        (int) metrologyWidth(0.96f), (int) metrologyHeight(0.88f));
    path.moveTo(metrologyWidth(0.49f), metrologyHeight(0.19f));
    path.lineTo(metrologyWidth(0.49f), metrologyHeight(0.29f));
    path.lineTo(metrologyWidth(0.20f), metrologyHeight(0.29f));
    path.lineTo(metrologyWidth(0.20f), metrologyHeight(0.39f));
    path.moveTo(metrologyWidth(0.20f), metrologyHeight(0.29f));
    path.lineTo(metrologyWidth(0.77f), metrologyHeight(0.29f));
    path.lineTo(metrologyWidth(0.77f), metrologyHeight(0.39f));
    path.moveTo(metrologyWidth(0.77f), metrologyHeight(0.53f));
    path.lineTo(metrologyWidth(0.77f), metrologyHeight(0.76f));
  }

  private float metrologyWidth(float percent) {
    return width * percent;
  }

  private float metrologyHeight(float percent) {
    return height * percent;
  }

}
