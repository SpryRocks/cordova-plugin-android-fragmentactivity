/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package ${mypackage};

/** extends CordovaActivity */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import ch.ti8m.vk.vicsdk.LocaleHelper;

public class MainActivity extends FragmentActivity
{
    private static final String TAG = "MainActivity";

    public uk.co.reallysmall.cordova.plugin.fragment.CordovaFragment currentFragment;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        if (!this.isTaskRoot()) {
            finish();
            return;
        }

        FragmentManager fm = getSupportFragmentManager();

        currentFragment = (uk.co.reallysmall.cordova.plugin.fragment.CordovaFragment) fm.findFragmentByTag("${mypackage}");

        if (currentFragment == null) {
            currentFragment = new uk.co.reallysmall.cordova.plugin.fragment.CordovaFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(android.R.id.content, currentFragment,"${mypackage}");
            ft.commit();
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        currentFragment.onSaveInstanceState(outState);
    }

    /**
     * Called when the activity receives a new intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        currentFragment.onNewIntent(intent);
    }

    /**
     * Called when view focus is changed
     */
    @SuppressLint("InlinedApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        currentFragment.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        currentFragment.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                            int[] grantResults) {
        currentFragment.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.INSTANCE.onAttach(newBase));
    }
}
