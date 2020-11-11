package com.sty.ne.jetpack.phonecall.jetpack_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.os.Bundle;

import com.sty.ne.jetpack.phonecall.jetpack_app.databinding.ActivityMainBinding;
import com.sty.ne.jetpack.phonecall.jetpack_app.utils.PermissionUtils;

/**
 * Activity 很轻松，体现了单一职责原则，只做一件事情，只做管理
 */
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private String[] needPermissions = { Manifest.permission.CALL_PHONE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //绑定管理操作
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //没有任何效果的实例化
        //viewModel = new MainViewModel(getApplication());

        //实例化(只适合 extends ViewModel)
        //viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);

        //实例化（适合 extends AndroidViewModel）
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MainViewModel.class);
        //绑定ViewModel
        binding.setVm(viewModel);
        //建立感应绑定，如果没有的话就无法感应
        binding.setLifecycleOwner(this);

        requestPermissions();
    }

    private void requestPermissions() {
        if (!PermissionUtils.checkPermissions(this, needPermissions)) {
            PermissionUtils.requestPermissions(this, needPermissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PermissionUtils.REQUEST_PERMISSIONS_CODE) {
            if (!PermissionUtils.verifyPermissions(grantResults)) {
                PermissionUtils.showMissingPermissionDialog(this);
            } else {

            }
        }
    }
}
