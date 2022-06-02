package com.example.pruebabinding;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.pruebabinding.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //Atributos

    private ArrayList<String> opciones;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        popularSpinner();
        binding.btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
            }
        });
    }

    private void popularSpinner() {
        opciones = new ArrayList<>(Arrays.asList("Escoja una operación","Suma","Resta","Multiplicación","División","Raíz (Número 2 es raíz)"));
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                opciones
        );
        binding.spOperacion.setAdapter(adaptador);
    }

    private void calcular() {
        if (binding.etNum1.getText().toString().isEmpty() ||
            binding.etNum2.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingrese los dos números", Toast.LENGTH_LONG).show();
        }
        else if(binding.spOperacion.getSelectedItem().equals("Escoja una operación")){
            Toast.makeText(this, "Escoja una operación", Toast.LENGTH_LONG).show();
        }
        else{
            operacion(Double.parseDouble(binding.etNum1.getText().toString()),
                    Double.parseDouble(binding.etNum2.getText().toString()),
                    binding.spOperacion.getSelectedItemPosition());
        }
    }

    private void operacion(double num1, double num2, int operacion) {
        double resultado = 0;
        switch (operacion){
            case 1:
                resultado = num1 + num2;
                break;
            case 2:
                resultado = num1 - num2;
                break;
            case 3:
                resultado = num1 * num2;
                break;
            case 4:
                resultado = num1 / num2;
                break;
            case 5:
                resultado = Math.pow(num1,1/num2);
                break;
        }
        String resultadoS = Double.valueOf(resultado).toString();
        binding.tvResultado.setText(resultadoS);
    }
}