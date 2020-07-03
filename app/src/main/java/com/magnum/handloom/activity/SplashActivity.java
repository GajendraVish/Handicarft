package com.magnum.handloom.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.magnum.handloom.R;
import com.magnum.handloom.response.UserBean;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static com.magnum.handloom.utilities.UserPreference.getPreference;
/**
 * Created by ITES-05 on 8/2/2017.
 */
public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        printHashKey(SplashActivity.this);
        Dexter.withActivity(SplashActivity.this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        final UserBean userBean = getPreference(SplashActivity.this);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SplashActivity.this, ""+userBean.getUsers_id(), Toast.LENGTH_SHORT).show();

                                /* Create an Intent that will start the Menu-Activity. */
                                if (userBean != null && userBean.getUsers_id() != null && userBean.getUsers_id().trim().length() > 0) {
                                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                                    startActivity(mainIntent);
                                    finish();
                                } else {
                                    Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                                    startActivity(mainIntent);
                                    finish();
                                }

                            }
                        }, 3000);

                    }
                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                        //   finish();
                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SplashActivity.this);
                        alertDialogBuilder.setTitle("Permissions Required")
                                .setMessage("You have forcefully denied some of the required permissions " +
                                        "for this action. Please open settings, go to permissions and allow them.")
                                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                                Uri.fromParts("package", SplashActivity.this.getPackageName(), null));
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivityForResult(intent, 5001);
                                        finish();
                                    }
                                })
//                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        finish();
//                                    }
//
//                                })
                                .setCancelable(false)
                                .create()
                                .show();


                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }

                }).check();
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public static void printHashKey(Context pContext) {
        try {
            PackageInfo info = pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i("keyhash", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.d("keyhash", "printHashKey()", e);
        } catch (Exception e) {
            Log.d("keyhash", "printHashKey()", e);
        }
    }
}
