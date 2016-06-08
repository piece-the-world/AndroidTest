package test.leo.com.designpattern;

import test.leo.com.designpattern.Behavioral.State.StateDemoFragment;
import test.leo.com.designpattern.Behavioral.Visitor.VisitorDemoFragment;
import test.leo.com.designpattern.Structual.Decorator.DecoratorDemoFragment;

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
      switch (childPosition) {
      case 0:
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.content_main, new DecoratorDemoFragment(), "decorator");
        ft.addToBackStack(null);
        ft.commit();
        break;
      }
      break;
    case 2:
      switch (childPosition) {
      case 0:
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.content_main, new StateDemoFragment(), "state");
        ft.addToBackStack(null);
        ft.commit();
        break;
      case 1:
        FragmentTransaction ft1 = fragmentManager.beginTransaction();
        ft1.add(R.id.content_main, new VisitorDemoFragment(), "visitor");
        ft1.addToBackStack(null);
        ft1.commit();
        break;
      }
      break;
    case 3:
      break;
    }
    return false;
  }
}
