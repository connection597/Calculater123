package com.example.calculater;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    final String CLEAR_INPUT_TEXT ="0";

    boolean isFirstlnput = true; // 입력 값이 처음 입력 되는가는 체크
    int resultNumber = 0; // 계산된 결과값을 저장하는 변수
    char operator = '+'; // 입력되 연산자를 저장하는 변수

    TextView resultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText = findViewById(R.id.result_text);
    }

//AC,CE,BS, . 이 클릭 되었을때 실행되는 메소드
    public void buttonClick(View view) {

        switch (view.getId()) {
            case R.id.all_clear_button:
                resultNumber = 0;
                operator = '+';
                setClearText(CLEAR_INPUT_TEXT);
                break;

            case R.id.clear_entry_button:
                setClearText(CLEAR_INPUT_TEXT);
                break;

            case R.id.back_space_button:
                if (resultText.getText().toString().length() > 1) {
                    String getResultText = resultText.getText().toString();
                    String subString = getResultText.substring(0, getResultText.length() - 1);
                    resultText.setText(subString);
                } else {
                    setClearText(CLEAR_INPUT_TEXT);
                }
                break;

            case R.id.decimal_button:
                Log.e("buttonClick",  "decimal_button 버튼이 클릭 되었습니다");
                break;

        }
    }


    // 입력된 숫자를 클리어 시켜 주는 메소드
    public void setClearText(String clearText) {
        isFirstlnput = true;
        resultText.setTextColor(0xFF898989);
        resultText.setText(clearText);

    }
    // 0~9 버튼이 클릭되었을때 실행되는 메소드
    public void numButtonClick(View view) {
        Button getButton = findViewById(view.getId());
        if (isFirstlnput) {
            resultText.setTextColor(0xFF000000);
            resultText.setText(getButton.getText().toString());
            isFirstlnput = false;
        } else {
            if (resultText.getText().toString().equals("0")) {
                Toast.makeText(getApplicationContext(), "0으로 시작하는 정수는 없습니다", Toast.LENGTH_SHORT).show();
                setClearText(CLEAR_INPUT_TEXT);
            } else {
                resultText.append(getButton.getText().toString());
            }
        }
    }



//연산자가 클릭 되었을때 실행되는 메소드
    public void operatorClick(View view) {
        Button getButton = findViewById(view.getId());

        if (view.getId() == R.id.result_button) {
            if(isFirstlnput){
                resultNumber = 0;
                operator = '+';
                setClearText("0");
                // TODO: 2020-10-28 다음에 실수형 계산기 만들때 윈도우 계산기 처럼 =을 두번이상 누를때 실행방법과 같이 구현 할것

            }else{
                resultNumber = intCal(resultNumber, Integer.parseInt(resultText.getText().toString()), operator);
                resultText.setText(String.valueOf(resultNumber));
                isFirstlnput = true;
            }

        } else {
            if(isFirstlnput){
                operator = getButton.getText().toString().charAt(0);
            }else{
                int lastNum = Integer.parseInt(resultText.getText().toString());
                resultNumber = intCal(resultNumber, lastNum, operator);
                operator = getButton.getText().toString().charAt(0);
                resultText.setText(String.valueOf(resultNumber));
                isFirstlnput = true;
            }
        }
    }



//사칙연산을 해서 값을 반환해주는 메소드
    public int intCal(int result, int lastNum, char operator) {
        if (operator == '+') {
            result += lastNum;
        } else if (operator == '-') {
            result -= lastNum;
        } else if (operator == '/') {
            result /= lastNum;
        } else if (operator == '*') {
            result *= lastNum;
        }
        return result;
    }

}
















