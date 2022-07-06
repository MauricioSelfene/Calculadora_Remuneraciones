package com.example.certamen1mauricioselfene;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText sueldobase1, diasTrabajados1, sueldoImponible1, bonos1;
    TextView resulSuedo, sueldoImponible, sueldoMenosAFP, sueldoMenossalud, sueldoMenosDescuentoSocial, sueldoTotalAPagar;
    Button   btnCalculoSueldo, btnCalcularLiquidacion;
    int sueldoSinBonos, totalSueldoImponible, totalAPF, totalSalud, totalesDescuentosSociales, totalAPagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sueldobase1 = findViewById(R.id.sueldoBase);
        diasTrabajados1 = findViewById(R.id.diasTrabajos);
        resulSuedo = findViewById(R.id.sueldoMensual);

        //bonos
        bonos1 = findViewById(R.id.bonos);
        sueldoImponible = findViewById(R.id.sueldoImponible);

        //leyes sociales
        sueldoMenosAFP = findViewById(R.id.AFP);
        sueldoMenossalud = findViewById(R.id.salud);
        sueldoMenosDescuentoSocial = findViewById(R.id.totalDescuento);
        sueldoTotalAPagar = findViewById(R.id.totalPagar);

    }

    public void CalcularSueldoPorDias(View view){
        boolean error = false;//
        if(sueldobase1.getText().toString().length() == 0) {
            sueldobase1.setError("Debe Ingresar el Sueldo Base");
            error = true;
        }
        if(diasTrabajados1.getText().toString().length() == 0) {
            diasTrabajados1.setError("Debe Ingresar el Sueldo Base");
            error = true;
        }

        if(error) {
            return;
        }

        int valorSueldoBase = Integer.parseInt(sueldobase1.getText().toString());
        int totalDiasTrabajados = Integer.parseInt(diasTrabajados1.getText().toString());

        if (valorSueldoBase < 326000)  {
            sueldobase1.setError("Sueldo Base debe ser mayor 326000");
            error = true;
        }
        if(totalDiasTrabajados > 30){
            diasTrabajados1.setError("Los dias Trabajados no deben ser mayores a 30 dias seguidos");
            error = true;
        }
        if(error) {
            return;
        }

        sueldoSinBonos = valorSueldoBase / 30 * totalDiasTrabajados;
        resulSuedo.setText("Sueldo a recibir es de: " + sueldoSinBonos);

    }

    public void CalcularSueldoImponible(View view){
        boolean error = false;
        if(sueldobase1.getText().toString().length() == 0) {
            sueldobase1.setError("Debe Ingresar el Sueldo Base");
            error = true;
        }
        if(diasTrabajados1.getText().toString().length() == 0) {
            diasTrabajados1.setError("Debe Ingresar el Sueldo Base");
            error = true;
        }
        if(bonos1.getText().toString().length() == 0) {
            bonos1.setError("Debe ingresar algun bono para calrcular el sueldo Imponible");
            error = true;
        }
        if(error) {
            return;
        }

        //el valor del bono no puede superar en 2.5 veces al sueldo calculado

        double limiteBono = sueldoSinBonos * 2.5;
        //sueldoImponible.setText("total: " + limiteBono);
        int bono = Integer.parseInt(bonos1.getText().toString());

        if (bono > limiteBono){
            bonos1.setError("El valor del bono no puede superar en 2.5 veces el sueldo calculado");
            error = true;
        }
        if(error) {
            return;
        }

        totalSueldoImponible = sueldoSinBonos + bono;
        sueldoImponible.setText("El sueldo imponible es de: " + totalSueldoImponible);

        totalAPF = (int) (totalSueldoImponible * 0.13);
        sueldoMenosAFP.setText("Descuento 13% APF: " + totalAPF);

        totalSalud = (int) (totalSueldoImponible * 0.07);
        sueldoMenossalud.setText("Descuento 7% Salud: " + totalSalud);

        totalesDescuentosSociales = totalAPF + totalSalud;
        sueldoMenosDescuentoSocial.setText("Total descuentos: " + totalesDescuentosSociales);

        totalAPagar = totalSueldoImponible - totalesDescuentosSociales;
        sueldoTotalAPagar.setText("Total a Pagar: " + totalAPagar);

    }

    public void LimpiaFormulario(View view){
        sueldobase1.setText(null);
        diasTrabajados1.setText(null);
        resulSuedo.setText(null);
        sueldoImponible.setText(null);
        bonos1.setText(null);
        sueldoMenosAFP.setText(null);
        sueldoMenossalud.setText(null);
        sueldoMenosDescuentoSocial.setText(null);
        sueldoTotalAPagar.setText(null);


    }

}