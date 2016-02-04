package test.leo.com.designpattern;

import test.leo.com.designpattern.Behavioral.State.StateDemoFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

public class MainActivity extends AppCompatActivity implements
    ExpandableListView.OnChildClickListener {

  public static final String TAG = "MainActivity";
  private CatalogAdapter catalogAdapter;
  private ExpandableListView expandableListView;
  private FragmentManager fragmentManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);
    initialize();

  }

  private void initialize() {
    fragmentManager = getSupportFragmentManager();
    catalogAdapter = new CatalogAdapter(this);
    expandableListView = (ExpandableListView) findViewById(R.id.elv1);
    expandableListView.setAdapter(catalogAdapter);
    expandableListView
        .setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
          @Override
          public boolean onGroupClick(ExpandableListView parent, View v,
              int groupPosition, long id) {
            Log.d(TAG, "onGroupClick:" + groupPosition);
            return false;
          }
        });
    expandableListView
        .setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
          @Override
          public void onGroupCollapse(int groupPosition) {
            Log.d(TAG, "onGroupCollapse:" + groupPosition);
          }
        });
    expandableListView
        .setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
          @Override
          public void onGroupExpand(int groupPosition) {
            Log.d(TAG, "onGroupExpand:" + groupPosition);
          }
        });
    expandableListView.setOnChildClickListener(MainActivity.this);
  }

  @Override
  public boolean onChildClick(ExpandableListView parent, View v,
      int groupPosition, int childPosition, long id) {
    Log.d(TAG, "onChildClick:" + groupPosition + "," + childPosition);
    switch (groupPosition) {
    case 0:
      break;
    case 1:

      break;
    case 2:
      switch (childPosition) {
      case 0:
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_main, new StateDemoFragment());
        ft.commit();
        break;
      }
      break;
    case 3:
      break;
    }
    return false;
  }
}
