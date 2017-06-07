package com.example.administrator.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int m=4;
    double p = 0;
    int[] TNiao= new int[4];//存储玩家拖鸟数目
    int[] HNiao = new int[4];//存储玩家活鸟数目
    int[] HXi = new int[4];//存储玩家胡子数目
    int[] resultTNiao = new int[4];//存储玩家拖鸟输赢结果
    int[] resultHNiao = new int[4];//存储玩家活鸟输赢结果
    EditText[] etTN = new EditText[4];
    EditText[] etHN = new EditText[4];
    EditText[] etHX = new EditText[4];
    private Button btExit,btnCalaulate,btReset;
    private EditText price;
    private TextView player1,player2,player3,player4,role4;
    private RadioButton three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCalaulate =(Button) findViewById(R.id.btn_JiSuan);
        price = (EditText)findViewById(R.id.Edit_01);
        etTN[0]= (EditText)findViewById(R.id.Edit_TuoNiao1);
        etTN[1]= (EditText)findViewById(R.id.Edit_TuoNiao2);
        etTN[2]= (EditText)findViewById(R.id.Edit_TuoNiao3);
        etTN[3]= (EditText)findViewById(R.id.Edit_TuoNiao4);
        etHN[0]= (EditText)findViewById(R.id.Edit_HuoNiao1);
        etHN[1]= (EditText)findViewById(R.id.Edit_HuoNiao2);
        etHN[2]= (EditText)findViewById(R.id.Edit_HuoNiao3);
        etHN[3]= (EditText)findViewById(R.id.Edit_HuoNiao4);
        etHX[0]= (EditText)findViewById(R.id.HuXi_01);
        etHX[1]= (EditText)findViewById(R.id.HuXi_02);
        etHX[2]= (EditText)findViewById(R.id.HuXi_03);
        etHX[3]= (EditText)findViewById(R.id.HuXi_04);
        player1 = (TextView) findViewById(R.id.JieGuo_01);
        player2 = (TextView) findViewById(R.id.JieGuo_02);
        player3 = (TextView) findViewById(R.id.JieGuo_03);
        player4 = (TextView) findViewById(R.id.JieGuo_04);
        role4 = (TextView)findViewById(R.id.Text_07);
        setNumber();
        three = (RadioButton) findViewById(R.id.player_three);
        //选择模式
        three.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(three.isChecked()==true)
                {
                    m=3;
                    role4.setEnabled(false);
                    etHN[3].setEnabled(false);
                    etTN[3].setEnabled(false);
                    etHX[3].setEnabled(false);
                    player4.setEnabled(false);
                }
                else{
                    m=4;
                    role4.setEnabled(true);
                    etHN[3].setEnabled(true);
                    etTN[3].setEnabled(true);
                    etHX[3].setEnabled(true);
                    player4.setEnabled(true);
                }
            }
        });
        //计算
        btnCalaulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                p = Double.parseDouble(price.getText().toString().equals("")?"0":price.getText().toString());
                for(int i=0;i<m;i++){
                    TNiao[i]=Integer.parseInt(etTN[i].getText().toString().equals("")?"0":etTN[i].getText().toString());
                    HNiao[i]=Integer.parseInt(etHN[i].getText().toString().equals("")?"0":etHN[i].getText().toString());
                    HXi[i]=Integer.parseInt(etHX[i].getText().toString().equals("")?"0":etHX[i].getText().toString());
                }
                retTuoNiao();
                huzi();
                retHuoNiao();
                player1.setText(resultHNiao[0]+resultTNiao[0]+"");
                player2.setText(resultHNiao[1]+resultTNiao[1]+"");
                player3.setText(resultHNiao[2]+resultTNiao[2]+"");
                player4.setText(resultHNiao[3]+resultTNiao[3]+"");
            }
        });
        //退出
        btExit = (Button) findViewById(R.id.btn_TuiChu);
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
        //清除数据
        btReset = (Button) findViewById(R.id.btn_QingChu);
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                price.setText("0");
                for(int i=0;i<m;i++){
                    etHN[i].setText("0");
                    etTN[i].setText("0");
                    etHX[i].setText("0");
                }
                player1.setText("0");
                player2.setText("0");
                player3.setText("0");
                player4.setText("0");
            }
        });

    }
    //数字键盘
    public void setNumber(){
        price.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        for(int i=0;i<4;i++) {
            etHN[i].setInputType(EditorInfo.TYPE_CLASS_NUMBER);
            etTN[i].setInputType(EditorInfo.TYPE_CLASS_NUMBER);
            etHX[i].setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        }
    }
    //拖鸟输赢
    public void retTuoNiao(){
        for(int i = 0;i<m;i++) {
            resultTNiao[i]=0;
            for(int j = 0;j<m;j++){
                if(HXi[i]!=HXi[j]){
                    if(HXi[i]>HXi[j])resultTNiao[i]+=(TN[i]+TN[j]);
                    else resultTNiao[i]-=(TN[i]+TN[j]);
                }
            }
        }
    }
    //胡子四舍五入
    public void huzi()
    {
        for(int i=0;i<4;i++)
        {
            if(Math.abs(HXi[i])==5) HXi[i]++;
            HXi[i]=(int) Math.rint(HXi[i]/10.0)*10;
        }
    }
    //计算活鸟
    public void retHuoNiao(){
        for(int i = 0;i<m;i++) {
            resultHNiao[i]=0;
            for(int j = 0;j<m;j++){
                resultHNiao[i]+=((HXi[i]-HXi[j])*p*(HN[i]+1)*(HN[j]+1));
            }
        }
    }

}

