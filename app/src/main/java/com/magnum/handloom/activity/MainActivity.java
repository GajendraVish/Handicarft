package com.magnum.handloom.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.magnum.handloom.R;
import com.magnum.handloom.adaptor.SlideListAdaptor;
import com.magnum.handloom.fragment.AboutUsFragment;
import com.magnum.handloom.fragment.ArticaalAddFragment;
import com.magnum.handloom.fragment.ArticalCategoryFragment;
import com.magnum.handloom.fragment.DashboardNewFragment;
import com.magnum.handloom.fragment.EcomCategoryListFragment;
import com.magnum.handloom.fragment.EventAddFragment;
import com.magnum.handloom.fragment.EventListFragment;
import com.magnum.handloom.fragment.OrderHistoryListFragment;
import com.magnum.handloom.response.LoginResponseBean;
import com.magnum.handloom.response.UserBean;
import com.magnum.handloom.utilities.UserPreference;

import br.com.joinersa.oooalertdialog.Animation;
import br.com.joinersa.oooalertdialog.OnClickListener;
import br.com.joinersa.oooalertdialog.OoOAlertDialog;

import static com.magnum.handloom.utilities.UserPreference.setPreference;


public class MainActivity extends AppCompatActivity {

    public Toolbar toolbar;
    DrawerLayout drawer;
    DashboardNewFragment dashboardFragment;
    FragmentManager mFragmentManager;
    TextView tv_title;
    TextView textView_count;
    RelativeLayout container_notification;

    TextView menu_profile_name, menu_profile_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        tv_title = (TextView) findViewById(R.id.tv_title);
        textView_count = (TextView) findViewById(R.id.textView_count);

        menu_profile_name = (TextView) findViewById(R.id.menu_profile_name);
        menu_profile_email = (TextView) findViewById(R.id.menu_profile_email);

        textView_count.setVisibility(View.INVISIBLE);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        container_notification = (RelativeLayout) findViewById(R.id.container_notification);
        container_notification.setVisibility(View.INVISIBLE);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setbackNavigation();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (getSupportFragmentManager().getBackStackEntryCount() <= 0) {
                    reIntailzeToolbar();
                } else {
                    setbackNavigation();
                }

            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        dashboardFragment = new DashboardNewFragment();
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.containerView, dashboardFragment).commit();

        ListView slidemenu_listview = (ListView) findViewById(R.id.slidemenu_listview);
        slidemenu_listview.setAdapter(new SlideListAdaptor(MainActivity.this));

        slidemenu_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawer.closeDrawer(GravityCompat.START);
                if (position == 0) {
                    closeallFragment();
                } else if (position == 1) {
                    drawer.closeDrawers();
                    ArticalCategoryFragment fragment = new ArticalCategoryFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", "Artical Category");
                    bundle.putString("old_title", "Artical Category");
                    fragment.setArguments(bundle);
                    AddFragment(fragment, "Artical Category");
                } else if (position == 2) {
                    drawer.closeDrawers();
                    EventListFragment fragment = new EventListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", "Events");
                    bundle.putString("old_title", "Events");
                    fragment.setArguments(bundle);
                    AddFragment(fragment, "Events");
                } else if (position == 3) {
                    drawer.closeDrawers();
                    EventAddFragment fragment = new EventAddFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", "Add Event");
                    bundle.putString("old_title", "Add Event");
                    fragment.setArguments(bundle);
                    AddFragment(fragment, "Add Event");
                } else if (position == 4) {
                    drawer.closeDrawers();
                    EcomCategoryListFragment fragment = new EcomCategoryListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", "Category");
                    bundle.putString("old_title", "Category");
                    fragment.setArguments(bundle);
                    AddFragment(fragment, "Category");
                } else if (position == 5) {

                    drawer.closeDrawers();
                    OrderHistoryListFragment fragment = new OrderHistoryListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", "Order history");
                    bundle.putString("old_title", "Order history");
                    fragment.setArguments(bundle);
                    AddFragment(fragment, "Order history");

                } else if (position == 6) {

                    drawer.closeDrawers();
                    AboutUsFragment fragment = new AboutUsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", "About us");
                    bundle.putString("old_title", "About us");
                    fragment.setArguments(bundle);
                    AddFragment(fragment, "About us");

                } else if (position == 7) {
                    showAlertDialogLogOut1();
                } else if (position == 8) {
                    finish();
                }

            }
        });

        menu_profile_name.setText(UserPreference.getPreference(MainActivity.this).getUsers_name());
        menu_profile_email.setText(UserPreference.getPreference(MainActivity.this).getUsers_email());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() <= 0) {

                if (dashboardFragment != null && dashboardFragment.isVisible()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(getString(R.string.app_name));
                    builder.setMessage("Do you want to exit ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                } else {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.add(R.id.containerView, dashboardFragment).commit();

                }
            } else {
                super.onBackPressed();
                if (getSupportFragmentManager().getBackStackEntryCount() <= 0) {
                    reIntailzeToolbar();
                }
            }
        }
    }


    public void AddFragment(Fragment fragment, String tag) {
        FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
//        fragTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragTransaction.add(R.id.containerView, fragment);
        fragTransaction.addToBackStack(tag);
        fragTransaction.commit();
        setbackNavigation();


    }

    public void setbackNavigation() {
        toolbar.setNavigationIcon(R.mipmap.back_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                closeFragment();
            }
        });
        container_notification.setVisibility(View.INVISIBLE);
    }

    public void RemoveFragment(Fragment fragment) {
        FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
        fragTransaction.remove(fragment);
        fragTransaction.commit();

    }

    public void closeFragment() {
        onBackPressed();
    }


    public void closeallFragment() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        reIntailzeToolbar();


    }


    public void reIntailzeToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        tv_title.setText(getString(R.string.app_name));
        toolbar.setNavigationIcon(R.mipmap.menu_button_big);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawers();
                    toolbar.setNavigationIcon(R.mipmap.menu_button_big);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                    toolbar.setNavigationIcon(R.mipmap.back_arrow);

                }


            }
        });
        container_notification.setVisibility(View.VISIBLE);


    }

    public void setHeader(String title) {
        tv_title.setText(title);
    }


    public void showAlertDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(getResources().getString(R.string.app_name));
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("अपडेट", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void showAlertDialogLogOut1() {
        OoOAlertDialog.Builder builder = new OoOAlertDialog.Builder(MainActivity.this);
        builder.setTitle("Alert");
        builder.setCancelable(false);
        builder.setMessage("Are you sure you want to logout.");
        builder.setAnimation(Animation.POP);
        builder.setPositiveButtonColor(R.color.sign_in_bg);
        builder.setNegativeButtonColor(R.color.sign_in_bg);
        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText, new OnClickListener() {
            @Override
            public void onClick() {
//                        positive button logic
//                        String token = " ";
//                        String u_id = UserPreference.getPreference(MainActivity.this).getId();
                UserBean userBean = new UserBean();
                setPreference(MainActivity.this, userBean);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
        String nagativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(nagativeText, null);
        builder.build();
    }
}
