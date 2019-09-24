package com.nguyenoanh.caclculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtInput;
    private TextView tvOutput;
    

    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;

    private Button btnPlus;
    private Button btnSub;
    private Button btnMul;
    private Button btnDiv;

    private Button btnPoint;
    private Button btnEqual;
    private Button btnClear;
    private Button btnClearAll;

    private Button btnOpen;
    private Button btnClose;

    private final String tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        setEventClickViews();
    }

    // Kết nối biến với các button
    private void initWidget() {
        edtInput = (EditText) findViewById(R.id.edtInput);
        tvOutput = (TextView) findViewById(R.id.tvOutput_result);

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);

        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);

        btnPoint = (Button) findViewById(R.id.btnPoint);
        btnEqual = (Button) findViewById(R.id.btnEqual);
        btnClear = (Button) findViewById(R.id.btnC);
        btnClearAll = (Button) findViewById(R.id.btnCE);

        btnOpen = (Button) findViewById(R.id.btnOpen);
        btnClose = (Button) findViewById(R.id.btnClose);
    }

    // Cho các view bắt sự kiện onClick
    public void setEventClickViews() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);

        btnPlus.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);

        btnPoint.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnClearAll.setOnClickListener(this);

        btnOpen.setOnClickListener(this);
        btnClose.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // Các số
            case R.id.btn0:
                edtInput.append("0");
                break;
            case R.id.btn1:
                edtInput.append("1");
                break;
            case R.id.btn2:
                edtInput.append("2");
                break;
            case R.id.btn3:
                edtInput.append("3");
                break;
            case R.id.btn4:
                edtInput.append("4");
                break;
            case R.id.btn5:
                edtInput.append("5");
                break;
            case R.id.btn6:
                edtInput.append("6");
                break;
            case R.id.btn7:
                edtInput.append("7");
                break;
            case R.id.btn8:
                edtInput.append("8");
                break;
            case R.id.btn9:
                edtInput.append("9");
                break;

            // Các ngoặc '(' và ')'
            case R.id.btnOpen:
                edtInput.append("(");
                break;
            case R.id.btnClose:
                edtInput.append(")");
                break;

            // Các toán tử
            case R.id.btnPlus:
                edtInput.append("+");
                break;
            case R.id.btnSub:
                edtInput.append("-");
                break;
            case R.id.btnMul:
                edtInput.append("x");
                break;
            case R.id.btnDiv:
                edtInput.append("/");
                break;

            // Các phím chức năng
            case R.id.btnPoint:
                edtInput.append(".");
                break;
            case R.id.btnC:
                String numberAfterRemove = deleteNumber (edtInput.getText ().toString ());
                edtInput.setText (numberAfterRemove);
                break;
            case R.id.btnCE:
                edtInput.setText("");
                tvOutput.setText("Result");
                break;
            case R.id.btnEqual:
                String pattern = "###.#######";
                DecimalFormat df = new DecimalFormat(pattern);

                double result = 0;
                addOperation(edtInput.getText().toString());
                addNumber(edtInput.getText().toString());

                // Thuật toán tính toán biểu thức
                if(arrOperation.size()>=arrNumber.size() ||arrOperation.size()<1){
                    tvOutput.setText("Error");
                }else {
                    for (int i = 0; i < (arrNumber.size() - 1); i++) {
                        switch (arrOperation.get(i)) {
                            case "+":
                                if (i == 0) {
                                    result = arrNumber.get(i) + arrNumber.get(i + 1);
                                } else {
                                    result = result + arrNumber.get(i + 1);
                                }
                                break;
                            case "-":
                                if (i == 0) {
                                    result = arrNumber.get(i) - arrNumber.get(i + 1);
                                } else {
                                    result = result - arrNumber.get(i + 1);
                                }
                                break;
                            case "x":
                                if (i == 0) {
                                    result = arrNumber.get(i) * arrNumber.get(i + 1);
                                } else {
                                    result = result * arrNumber.get(i + 1);
                                }
                                break;
                            case "/":
                                if (i == 0) {
                                    result = arrNumber.get(i) / arrNumber.get(i + 1);
                                } else {
                                    result = result / arrNumber.get(i + 1);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    Log.d(tag, "OnClick: "+result);
                    // Format lại kết quả hiển thị
                    tvOutput.setText(df.format(result) + "");
                }
        }
    }

    //Mảng chứa các toán tử
    public ArrayList<String> arrOperation;
    //Mảng chứa các số
    public ArrayList<Double> arrNumber;

    //Lấy tất cả các phép tính lưu vào mảng arrOperation
    public int addOperation(String input) {
        arrOperation = new ArrayList<>();

        char[] cArray = input.toCharArray();
        for (int i = 0; i < cArray.length; i++) {
            switch (cArray[i]) {
                case '+':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '-':
                    arrOperation.add(cArray[i] + "");
                    break;
                case 'x':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '/':
                    arrOperation.add(cArray[i] + "");
                    break;
                default:
                    break;
            }
        }
        return 0;
    }

    public String deleteNumber(String number){
        int length = number.length ();
        String temp = number.substring (0, length - 1);
        return temp;
    }

    //Lấy tất cả các số lưu vào mảng arrNumber
    public void addNumber(String stringInput) {
        arrNumber = new ArrayList<>();
        // Lấy ra các con số
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while(matcher.find()){
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }

}
