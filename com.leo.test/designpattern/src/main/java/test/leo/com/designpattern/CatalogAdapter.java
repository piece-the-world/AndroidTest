package test.leo.com.designpattern;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

/**
 * Created by leo on 16-2-1.
 */
public class CatalogAdapter extends BaseExpandableListAdapter {

  static final String TAG = "catalog";
  List<String> catalogGroup;
  List<List<String>> catalogItem;
  Context context;
  private Matcher matcher;

  public CatalogAdapter(Context context) {
    this.context = context;
    loadData();
  }

  private void loadData() {
    catalogGroup = new ArrayList<String>() {
      {
        add("Creational");
        add("Stuctual");
        add("Behavioral");
        add("Concurrency");
      }
    };
    catalogItem = new ArrayList<>();
    for (int i = 0; i < catalogGroup.size(); i++) {
      catalogItem.add(new ArrayList<String>());
    }
    Pattern[] patterns = new Pattern[4];
    patterns[0] = Pattern
        .compile("test.leo.com.designpattern.Creational.([a-zA-Z]+).[a-zA-Z]+DemoFragment");
    patterns[1] = Pattern
        .compile("test.leo.com.designpattern.Stuctual.([a-zA-Z]+).[a-zA-Z]+DemoFragment");
    patterns[2] = Pattern
        .compile("test.leo.com.designpattern.Behavioral.([a-zA-Z]+).[a-zA-Z]+DemoFragment");
    patterns[3] = Pattern
        .compile("test.leo.com.designpattern.Concurrency.([a-zA-Z]+).[a-zA-Z]+DemoFragment");
    try {
      PathClassLoader classLoader = (PathClassLoader) Thread.currentThread()
          .getContextClassLoader();

      // Field dexField = findField(classLoader, "mDexs");
      // DexFile[] dexs = (DexFile[]) dexField.get(classLoader);

      Field pathListField = ReflectUtils.findField(classLoader, "pathList");
      Object dexPathList = pathListField.get(classLoader);
      Field dexElementsField = ReflectUtils.findField(dexPathList,
          "dexElements");
      Object[] dexElements = (Object[]) dexElementsField.get(dexPathList);

      Field dexFileField = ReflectUtils.findField(dexElements[0], "dexFile");
      DexFile dexFile = (DexFile) dexFileField.get(dexElements[0]);
      Enumeration<String> entries = dexFile.entries();
      while (entries.hasMoreElements()) {
        String entry = entries.nextElement();
        if ((!TextUtils.isEmpty(entry))
            && entry.startsWith("test.leo.com.designpattern")) {
          Log.d(TAG, entry);
          for (int i = 0; i < catalogGroup.size(); i++) {
            matcher = patterns[i].matcher(entry);
            if (matcher.find()) {
              catalogItem.get(i).add(matcher.group(1));
            }
          }
        }
      }
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
  }

  private TextView createView(String content) {
    AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
        ViewGroup.LayoutParams.FILL_PARENT, 38);
    TextView text = new TextView(context);
    text.setLayoutParams(layoutParams);
    text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
    text.setPadding(40, 0, 0, 0);
    text.setText(content);
    return text;
  }

  @Override
  public int getGroupCount() {
    return catalogGroup.size();
  }

  @Override
  public int getChildrenCount(int groupPosition) {
    return catalogItem.get(groupPosition).size();
  }

  @Override
  public Object getGroup(int groupPosition) {
    return catalogGroup.get(groupPosition);
  }

  @Override
  public Object getChild(int groupPosition, int childPosition) {
    return catalogItem.get(groupPosition).get(childPosition);
  }

  @Override
  public long getGroupId(int groupPosition) {
    return 0;
  }

  @Override
  public long getChildId(int groupPosition, int childPosition) {
    return 0;
  }

  @Override
  public boolean hasStableIds() {
    return false;
  }

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded,
      View convertView, ViewGroup parent) {
    TextView textView = null;
    if (convertView != null) {
      textView = (TextView) convertView;
      textView.setText(catalogGroup.get(groupPosition));
    } else {
      textView = createView(catalogGroup.get(groupPosition));
    }
    return textView;
  }

  @Override
  public View getChildView(int groupPosition, int childPosition,
      boolean isLastChild, View convertView, ViewGroup parent) {
    TextView textView = null;
    if (convertView != null) {
      textView = (TextView) convertView;
      textView.setText(catalogItem.get(groupPosition).get(childPosition));
    } else {
      textView = createView(catalogItem.get(groupPosition).get(childPosition));
    }
    return textView;
  }

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return false;
  }
}
