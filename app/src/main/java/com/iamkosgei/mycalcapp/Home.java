package com.iamkosgei.mycalcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.iamkosgei.mycalcapp.databinding.HomeBinding;
import com.iamkosgei.mycalcapp.services.CalcService;

public class Home extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Home";
   private ICalcService calcService;
   private  CalcServiceConnection calcServiceConnection;
    private HomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //set click listeners
        binding.add.setOnClickListener(this);
        binding.divide.setOnClickListener(this);
        binding.subtract.setOnClickListener(this);
        binding.multiply.setOnClickListener(this);

        createConnection();
    }

    private void createConnection() {
        calcServiceConnection = new CalcServiceConnection();
        Intent i = new Intent();
        i.setAction("com.iamkosgei");
        i.setPackage(this.getPackageName());
        bindService(i, calcServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View view) {
        //get the input values
        double number1 = Double.parseDouble(binding.num1.getText().toString());
        double number2 = Double.parseDouble(binding.num2.getText().toString());

        switch (view.getId()){
            case R.id.add:
                try{
                    binding.result.setText(String.valueOf(calcService.add(number1, number2)));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.divide:
                try{
                    binding.result.setText(String.valueOf(calcService.divide(number1, number2)));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.subtract:
                try{
                    binding.result.setText(String.valueOf(calcService.subtract(number1, number2)));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.multiply:
                try{
                    binding.result.setText(String.valueOf(calcService.multiply(number1, number2)));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    class CalcServiceConnection implements ServiceConnection{
        public void onServiceConnected(ComponentName name, IBinder boundService) {
            calcService = ICalcService.Stub.asInterface((IBinder) boundService);
        }

        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(Home.this, "errooor", Toast.LENGTH_SHORT).show();
            calcService = null;
        }
    }



}