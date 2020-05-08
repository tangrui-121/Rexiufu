package com.text.rexiufu.zhiwen;

import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.text.rexiufu.R;

import org.strongswan.android.fingerprint.FingerprintCallback;
import org.strongswan.android.fingerprint.FingerprintVerifyManager;

import androidx.core.content.ContextCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.fragment.app.FragmentActivity;

/**
 * 指纹识别Demo
 *  .
 */
public class FingerDemoActivity extends FragmentActivity {
    private  FingerprintManagerCompat compat;
    private FingerprintCallback fingerprintCallback;

    private Spinner spinner_profile_proposals_esp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_finger);
        findViewById(R.id.tvFingerprint).setOnClickListener(v -> {
            fingerprint();
        });

        compat = FingerprintManagerCompat.from(this);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SDK version is "+ Build.VERSION.SDK_INT);
        stringBuilder.append("\n");
        stringBuilder.append("isHardwareDetected : "+compat.isHardwareDetected());
        stringBuilder.append("\n");
        stringBuilder.append("hasEnrolledFingerprints : "+compat.hasEnrolledFingerprints());
        stringBuilder.append("\n");
        stringBuilder.append("isKeyguardSecure : "+isKeyguardSecure());
        stringBuilder.append("\n");

        spinner_profile_proposals_esp = findViewById(R.id.spinner_profile_proposals_esp);
        spinner_profile_proposals_esp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("111",spinner_profile_proposals_esp.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }




    private void fingerprint() {
        FingerprintVerifyManager.Builder builder = new FingerprintVerifyManager.Builder(FingerDemoActivity.this);
        inifingerprint();
        builder.callback(fingerprintCallback)
                .fingerprintColor(ContextCompat.getColor(FingerDemoActivity.this, R.color.colorPrimary))
                .build();
    }

    private void inifingerprint(){
        fingerprintCallback = new FingerprintCallback() {
            @Override
            public void onSucceeded() {
                Toast.makeText(FingerDemoActivity.this, getString(R.string.biometricprompt_verify_success), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FingerDemoActivity.this, TargetActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailed() {
                Toast.makeText(FingerDemoActivity.this, getString(R.string.biometricprompt_verify_failed), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUsepwd() {
                Toast.makeText(FingerDemoActivity.this, getString(R.string.fingerprint_usepwd), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(FingerDemoActivity.this, getString(R.string.fingerprint_cancel), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onHwUnavailable() {
                Toast.makeText(FingerDemoActivity.this, getString(R.string.biometricprompt_finger_hw_unavailable), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNoneEnrolled() {
                //弹出提示框，跳转指纹添加页面
                AlertDialog.Builder lertDialogBuilder = new AlertDialog.Builder(FingerDemoActivity.this);
                lertDialogBuilder.setTitle(getString(R.string.biometricprompt_tip))
                        .setMessage(getString(R.string.biometricprompt_finger_add))
                        .setCancelable(false)
                        .setNegativeButton(getString(R.string.biometricprompt_finger_add_confirm), ((DialogInterface dialog, int which) -> {
                            Intent intent = new Intent(Settings.ACTION_FINGERPRINT_ENROLL);
                            startActivity(intent);
                        }
                        ))
                        .setPositiveButton(getString(R.string.biometricprompt_cancel), ((DialogInterface dialog, int which) -> {
                            dialog.dismiss();
                        }
                        ))
                        .create().show();
            }

        };
    }


    public boolean isKeyguardSecure() {
        KeyguardManager keyguardManager = (KeyguardManager) this.getSystemService(Context.KEYGUARD_SERVICE);
        if (keyguardManager.isKeyguardSecure()) {
            return true;
        }

        return false;
    }
}
