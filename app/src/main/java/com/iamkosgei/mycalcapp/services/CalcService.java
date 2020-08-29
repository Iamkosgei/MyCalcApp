package com.iamkosgei.mycalcapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.iamkosgei.mycalcapp.ICalcService;

public class CalcService extends Service {
    private static final String TAG = "CalcService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ICalcService.Stub() {
            @Override
            public double add(double x, double y) throws RemoteException {
                return x+y;
            }

            @Override
            public double subtract(double x, double y) throws RemoteException {
                return x-y;
            }

            @Override
            public double divide(double x, double y) throws RemoteException {
                return x/y;
            }

            @Override
            public double multiply(double x, double y) throws RemoteException {
                return x*y;
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"destroyed");
    }
}
