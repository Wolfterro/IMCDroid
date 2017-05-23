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

/**
 * Created by Wolfterro on 22/05/2017.
 */

// Classe responsável por calcular o índice de massa corporal do usuário
// =====================================================================
public class IMC {

    private double weight;
    private double height;
    private double result;
    private int rank;

    // Construtor da classe IMC usando Strings
    // =======================================
    public IMC(String weight, String height) {
        this.weight = Double.parseDouble(weight);
        this.height = Double.parseDouble(height);
    }

    // Construtor da classe IMC usando Double
    // ======================================
    public IMC(Double weight, Double height) {
        this.weight = weight;
        this.height = height;
    }

    // Método de entrada da classe, responsável por
    // iniciar os cálculos e gerar a classificação
    // ============================================
    public void calculate() {
        result = imccalc();
        rank = ranking();
    }

    // Resgatando resultado
    // ====================
    public double getResult() {
        return result;
    }

    // Resgatando classificação
    // ========================
    public int getRank() {
        return rank;
    }

    // Calculando IMC
    // ==============
    private double imccalc() {
        return weight / ((height / 100) * (height / 100));
    }

    // Classificando resultado:
    // 0 - Sem classificação
    // 1 - Magreza grave
    // 2 - Magreza moderada
    // 3 - Magreza leve
    // 4 - Saudável
    // 5 - Sobrepeso
    // 6 - Obesidade Grau I
    // 7 - Obesidade Grau II
    // 8 - Obesidade Grau III
    // =======================
    private int ranking() {
        if(result < 16.0) {
            return 1;
        }
        else if(result == 16.0 || result < 17.0) {
            return 2;
        }
        else if(result == 17.0 || result < 18.5) {
            return 3;
        }
        else if(result == 18.5 || result < 25.0) {
            return 4;
        }
        else if(result == 25.0 || result < 30.0) {
            return 5;
        }
        else if(result == 30.0 || result < 35.0) {
            return 6;
        }
        else if(result == 35.0 || result < 40.0) {
            return 7;
        }
        else if(result >= 40.0){
            return 8;
        }
        else {
            return 0;
        }
    }
}
