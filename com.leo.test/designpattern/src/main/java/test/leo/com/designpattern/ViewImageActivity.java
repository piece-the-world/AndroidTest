package test.leo.com.designpattern;

import test.leo.com.designpattern.view.ScaleableImageView;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

/**
 * Created by taochao on 16/6/6.
 */
public class ViewImageActivity extends Activity {
  public static final java.lang.String IMAGE_NAME = "image_name";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle bundle = getIntent().getExtras();
    String imageName = bundle.getString(IMAGE_NAME);
    setContentView(R.layout.activity_view_image);
    if (!TextUtils.isEmpty(imageName)) {
      int resId = getResources().getIdentifier(imageName, "drawable",
          "test.leo.com.designpattern");
      if (resId != 0) {
        ((ScaleableImageView) findViewById(R.id.image)).setImageResource(resId);
      }
    }

  }
}
