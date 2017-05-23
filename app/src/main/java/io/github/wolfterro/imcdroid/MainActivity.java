/*
The MIT License (MIT)

Copyright (c) 2017 Wolfgang Almeida

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package io.github.wolfterro.imcdroid;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText1;     // Peso em quilogramas
    private EditText editText2;     // Altura em centímetros
    private Button button1;         // Botão principal para iniciar o cálculo
    private TextView textViewResult;            // Resultado do IMC
    private TextView textViewClassification;    // Classificação do IMC

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        textViewResult = (TextView)findViewById(R.id.textViewResult);
        textViewClassification = (TextView)findViewById(R.id.textViewClassification);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String weight = editText1.getText().toString();
                final String height = editText2.getText().toString();
                AlertDialog.Builder errorMessage = new AlertDialog.Builder(MainActivity.this);

                if(weight.equals("") || weight == null) {
                    errorMessage.setTitle(getString(R.string.error));
                    errorMessage.setMessage(getString(R.string.errorInsertWeight));
                    errorMessage.setPositiveButton("OK", null);
                    errorMessage.setCancelable(false);
                    errorMessage.create().show();
                }
                else if(height.equals("") || height == null) {
                    errorMessage.setTitle(getString(R.string.error));
                    errorMessage.setMessage(getString(R.string.errorInsertHeight));
                    errorMessage.setPositiveButton("OK", null);
                    errorMessage.setCancelable(false);
                    errorMessage.create().show();
                }
                else {
                    IMC imc = new IMC(editText1.getText().toString(),
                            editText2.getText().toString());
                    imc.calculate();

                    textViewResult.setText(String.format("%.2f",imc.getResult()));
                    switch (imc.getRank()) {
                        case 1:
                            textViewClassification.setText(getString(R.string.graveMeagerness));
                            break;
                        case 2:
                            textViewClassification.setText(getString(R.string.moderateMeagerness));
                            break;
                        case 3:
                            textViewClassification.setText(getString(R.string.slightMeagerness));
                            break;
                        case 4:
                            textViewClassification.setText(getString(R.string.healthy));
                            break;
                        case 5:
                            textViewClassification.setText(getString(R.string.overweight));
                            break;
                        case 6:
                            textViewClassification.setText(getString(R.string.obesityLevelOne));
                            break;
                        case 7:
                            textViewClassification.setText(getString(R.string.obesityLevelTwo));
                            break;
                        case 8:
                            textViewClassification.setText(getString(R.string.obesityLevelThree));
                            break;
                        default:
                            textViewClassification.setText(getString(
                                    R.string.classificationValueDefault));
                            break;
                    }
                }
            }
        });
    }
}
