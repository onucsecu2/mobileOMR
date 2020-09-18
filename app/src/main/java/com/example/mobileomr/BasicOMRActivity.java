package com.example.mobileomr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class BasicOMRActivity extends AppCompatActivity {
    private EditText NofQ;
    private EditText name;
    private Button save;
    private int[] ansArr=new int [81];
    public String Qname;
    public int no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_omr);
        name=(EditText)findViewById(R.id.name);
        NofQ=(EditText)findViewById(R.id.NofQ);
        save=(Button)findViewById(R.id.Save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Qname= String.valueOf(name.getText());
            }
        });

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.a1:
                if (checked) {
                    ansArr[1] = 1;
                    ansArr[6] = 0;
                    ansArr[11] = 0;
                    ansArr[16] = 0;
                    break;
                }
            case R.id.b1:
                if (checked){
                    ansArr[1]=0;
                    ansArr[6]=1;
                    ansArr[11]=0;
                    ansArr[16]=0;
                    break;
                }
            case R.id.c1:
                if (checked) {
                    ansArr[1] = 0;
                    ansArr[6] = 0;
                    ansArr[11] = 1;
                    ansArr[16] = 0;
                    break;
                }
            case R.id.d1:
                if (checked){
                    ansArr[1]=0;
                    ansArr[6]=0;
                    ansArr[11]=0;
                    ansArr[16]=1;
                    break;
                }
            case R.id.a2:
                if (checked) {
                    ansArr[2] = 1;
                    ansArr[7] = 0;
                    ansArr[12] = 0;
                    ansArr[17] = 0;
                    break;
                }
            case R.id.b2:
                if (checked){
                    ansArr[2]=0;
                    ansArr[7]=1;
                    ansArr[12]=0;
                    ansArr[17]=0;
                    break;
                }
            case R.id.c2:
                if (checked) {
                    ansArr[2] = 0;
                    ansArr[7] = 0;
                    ansArr[12] = 1;
                    ansArr[17] = 0;
                    break;
                }
            case R.id.d2:
                if (checked){
                    ansArr[2]=0;
                    ansArr[7]=0;
                    ansArr[12]=0;
                    ansArr[17]=1;
                    break;
                }
            case R.id.a3:
                if (checked) {
                    ansArr[3] = 1;
                    ansArr[8] = 0;
                    ansArr[13] = 0;
                    ansArr[18] = 0;
                    break;
                }
            case R.id.b3:
                if (checked){
                    ansArr[3]=0;
                    ansArr[8]=1;
                    ansArr[13]=0;
                    ansArr[18]=0;
                    break;
                }
            case R.id.c3:
                if (checked) {
                    ansArr[3] = 0;
                    ansArr[8] = 0;
                    ansArr[13] = 1;
                    ansArr[18] = 0;
                    break;
                }
            case R.id.d3:
                if (checked){
                    ansArr[3]=0;
                    ansArr[8]=0;
                    ansArr[13]=0;
                    ansArr[18]=1;
                    break;
                }
            case R.id.a4:
                if (checked) {
                    ansArr[4] = 1;
                    ansArr[9] = 0;
                    ansArr[14] = 0;
                    ansArr[19] = 0;
                    break;
                }
            case R.id.b4:
                if (checked){
                    ansArr[4]=0;
                    ansArr[9]=1;
                    ansArr[14]=0;
                    ansArr[19]=0;
                    break;
                }
            case R.id.c4:
                if (checked) {
                    ansArr[4] = 0;
                    ansArr[9] = 0;
                    ansArr[14] = 1;
                    ansArr[19] = 0;
                    break;
                }
            case R.id.d4:
                if (checked){
                    ansArr[4]=0;
                    ansArr[9]=0;
                    ansArr[14]=0;
                    ansArr[19]=1;
                    break;
                }
            case R.id.a5:
                if (checked) {
                    ansArr[5] = 1;
                    ansArr[10] = 0;
                    ansArr[15] = 0;
                    ansArr[20] = 0;
                    break;
                }
            case R.id.b5:
                if (checked){
                    ansArr[5]=0;
                    ansArr[10]=1;
                    ansArr[15]=0;
                    ansArr[20]=0;
                    break;
                }
            case R.id.c5:
                if (checked) {
                    ansArr[5] = 0;
                    ansArr[10] = 0;
                    ansArr[15] = 1;
                    ansArr[20] = 0;
                    break;
                }
            case R.id.d5:
                if (checked){
                    ansArr[5]=0;
                    ansArr[10]=0;
                    ansArr[15]=0;
                    ansArr[20]=1;
                    break;
                }
            case R.id.a6:
                if (checked) {
                    ansArr[21] = 1;
                    ansArr[26] = 0;
                    ansArr[31] = 0;
                    ansArr[36] = 0;
                    break;
                }
            case R.id.b6:
                if (checked){
                    ansArr[21]=0;
                    ansArr[26]=1;
                    ansArr[31]=0;
                    ansArr[36]=0;
                    break;
                }
            case R.id.c6:
                if (checked) {
                    ansArr[21] = 0;
                    ansArr[26] = 0;
                    ansArr[31] = 1;
                    ansArr[36] = 0;
                    break;
                }
            case R.id.d6:
                if (checked){
                    ansArr[21]=0;
                    ansArr[26]=0;
                    ansArr[31]=0;
                    ansArr[36]=1;
                    break;
                }
            case R.id.a7:
                if (checked) {
                    ansArr[22] = 1;
                    ansArr[27] = 0;
                    ansArr[32] = 0;
                    ansArr[37] = 0;
                    break;
                }
            case R.id.b7:
                if (checked){
                    ansArr[22]=0;
                    ansArr[27]=1;
                    ansArr[32]=0;
                    ansArr[37]=0;
                    break;
                }
            case R.id.c7:
                if (checked) {
                    ansArr[22] = 0;
                    ansArr[27] = 0;
                    ansArr[32] = 1;
                    ansArr[37] = 0;
                    break;
                }
            case R.id.d7:
                if (checked){
                    ansArr[22]=0;
                    ansArr[27]=0;
                    ansArr[32]=0;
                    ansArr[37]=1;
                    break;
                }
            case R.id.a8:
                if (checked) {
                    ansArr[23] = 1;
                    ansArr[28] = 0;
                    ansArr[33] = 0;
                    ansArr[38] = 0;
                    break;
                }
            case R.id.b8:
                if (checked){
                    ansArr[23]=0;
                    ansArr[28]=1;
                    ansArr[33]=0;
                    ansArr[38]=0;
                    break;
                }
            case R.id.c8:
                if (checked) {
                    ansArr[23] = 0;
                    ansArr[28] = 0;
                    ansArr[33] = 1;
                    ansArr[38] = 0;
                    break;
                }
            case R.id.d8:
                if (checked){
                    ansArr[23]=0;
                    ansArr[28]=0;
                    ansArr[33]=0;
                    ansArr[38]=1;
                    break;
                }
            case R.id.a9:
                if (checked) {
                    ansArr[24] = 1;
                    ansArr[29] = 0;
                    ansArr[34] = 0;
                    ansArr[39] = 0;
                    break;
                }
            case R.id.b9:
                if (checked){
                    ansArr[24]=0;
                    ansArr[29]=1;
                    ansArr[34]=0;
                    ansArr[39]=0;
                    break;
                }
            case R.id.c9:
                if (checked) {
                    ansArr[24] = 0;
                    ansArr[29] = 0;
                    ansArr[34] = 1;
                    ansArr[39] = 0;
                    break;
                }
            case R.id.d9:
                if (checked){
                    ansArr[24]=0;
                    ansArr[29]=0;
                    ansArr[34]=0;
                    ansArr[39]=1;
                    break;
                }
            case R.id.a10:
                if (checked) {
                    ansArr[25] = 1;
                    ansArr[30] = 0;
                    ansArr[35] = 0;
                    ansArr[40] = 0;
                    break;
                }
            case R.id.b10:
                if (checked){
                    ansArr[25]=0;
                    ansArr[30]=1;
                    ansArr[35]=0;
                    ansArr[40]=0;
                    break;
                }
            case R.id.c10:
                if (checked) {
                    ansArr[25] = 0;
                    ansArr[30] = 0;
                    ansArr[35] = 1;
                    ansArr[40] = 0;
                    break;
                }
            case R.id.d10:
                if (checked){
                    ansArr[25]=0;
                    ansArr[30]=0;
                    ansArr[35]=0;
                    ansArr[40]=1;
                    break;
                }
            case R.id.a11:
                if (checked) {
                    ansArr[41] = 1;
                    ansArr[46] = 0;
                    ansArr[51] = 0;
                    ansArr[56] = 0;
                    break;
                }
            case R.id.b11:
                if (checked){
                    ansArr[41]=0;
                    ansArr[46]=1;
                    ansArr[51]=0;
                    ansArr[56]=0;
                    break;
                }
            case R.id.c11:
                if (checked) {
                    ansArr[41] = 0;
                    ansArr[46] = 0;
                    ansArr[51] = 1;
                    ansArr[56] = 0;
                    break;
                }
            case R.id.d11:
                if (checked){
                    ansArr[41]=0;
                    ansArr[46]=0;
                    ansArr[51]=0;
                    ansArr[56]=1;
                    break;
                }
            case R.id.a12:
                if (checked) {
                    ansArr[42] = 1;
                    ansArr[47] = 0;
                    ansArr[52] = 0;
                    ansArr[57] = 0;
                    break;
                }
            case R.id.b12:
                if (checked){
                    ansArr[42]=0;
                    ansArr[47]=1;
                    ansArr[52]=0;
                    ansArr[57]=0;
                    break;
                }
            case R.id.c12:
                if (checked) {
                    ansArr[42] = 0;
                    ansArr[47] = 0;
                    ansArr[52] = 1;
                    ansArr[57] = 0;
                    break;
                }
            case R.id.d12:
                if (checked){
                    ansArr[42]=0;
                    ansArr[47]=0;
                    ansArr[52]=0;
                    ansArr[57]=1;
                    break;
                }
            case R.id.a13:
                if (checked) {
                    ansArr[43] = 1;
                    ansArr[48] = 0;
                    ansArr[53] = 0;
                    ansArr[58] = 0;
                    break;
                }
            case R.id.b13:
                if (checked){
                    ansArr[43]=0;
                    ansArr[48]=1;
                    ansArr[53]=0;
                    ansArr[58]=0;
                    break;
                }
            case R.id.c13:
                if (checked) {
                    ansArr[43] = 0;
                    ansArr[48] = 0;
                    ansArr[53] = 1;
                    ansArr[58] = 0;
                    break;
                }
            case R.id.d13:
                if (checked){
                    ansArr[43]=0;
                    ansArr[48]=0;
                    ansArr[53]=0;
                    ansArr[58]=1;
                    break;
                }
            case R.id.a14:
                if (checked) {
                    ansArr[44] = 1;
                    ansArr[49] = 0;
                    ansArr[54] = 0;
                    ansArr[59] = 0;
                    break;
                }
            case R.id.b14:
                if (checked){
                    ansArr[44]=0;
                    ansArr[49]=1;
                    ansArr[54]=0;
                    ansArr[59]=0;
                    break;
                }
            case R.id.c14:
                if (checked) {
                    ansArr[44] = 0;
                    ansArr[49] = 0;
                    ansArr[54] = 1;
                    ansArr[59] = 0;
                    break;
                }
            case R.id.d14:
                if (checked){
                    ansArr[44]=0;
                    ansArr[49]=0;
                    ansArr[54]=0;
                    ansArr[59]=1;
                    break;
                }
            case R.id.a15:
                if (checked) {
                    ansArr[45] = 1;
                    ansArr[50] = 0;
                    ansArr[55] = 0;
                    ansArr[60] = 0;
                    break;
                }
            case R.id.b15:
                if (checked){
                    ansArr[45]=0;
                    ansArr[50]=1;
                    ansArr[55]=0;
                    ansArr[60]=0;
                    break;
                }
            case R.id.c15:
                if (checked) {
                    ansArr[45] = 0;
                    ansArr[50] = 0;
                    ansArr[55] = 1;
                    ansArr[60] = 0;
                    break;
                }
            case R.id.d15:
                if (checked){
                    ansArr[45]=0;
                    ansArr[50]=0;
                    ansArr[55]=0;
                    ansArr[60]=1;
                    break;
                }
            case R.id.a16:
                if (checked) {
                    ansArr[61] = 1;
                    ansArr[66] = 0;
                    ansArr[71] = 0;
                    ansArr[76] = 0;
                    break;
                }
            case R.id.b16:
                if (checked){
                    ansArr[61]=0;
                    ansArr[66]=1;
                    ansArr[71]=0;
                    ansArr[76]=0;
                    break;
                }
            case R.id.c16:
                if (checked) {
                    ansArr[61] = 0;
                    ansArr[66] = 0;
                    ansArr[71] = 1;
                    ansArr[76] = 0;
                    break;
                }
            case R.id.d16:
                if (checked){
                    ansArr[61]=0;
                    ansArr[66]=0;
                    ansArr[71]=0;
                    ansArr[76]=1;
                    break;
                }
            case R.id.a17:
                if (checked) {
                    ansArr[62] = 1;
                    ansArr[67] = 0;
                    ansArr[72] = 0;
                    ansArr[77] = 0;
                    break;
                }
            case R.id.b17:
                if (checked){
                    ansArr[62]=0;
                    ansArr[67]=1;
                    ansArr[72]=0;
                    ansArr[77]=0;
                    break;
                }
            case R.id.c17:
                if (checked) {
                    ansArr[62] = 0;
                    ansArr[67] = 0;
                    ansArr[72] = 1;
                    ansArr[77] = 0;
                    break;
                }
            case R.id.d17:
                if (checked){
                    ansArr[62]=0;
                    ansArr[67]=0;
                    ansArr[72]=0;
                    ansArr[77]=1;
                    break;
                }
            case R.id.a18:
                if (checked) {
                    ansArr[63] = 1;
                    ansArr[68] = 0;
                    ansArr[73] = 0;
                    ansArr[78] = 0;
                    break;
                }
            case R.id.b18:
                if (checked){
                    ansArr[63]=0;
                    ansArr[68]=1;
                    ansArr[73]=0;
                    ansArr[78]=0;
                    break;
                }
            case R.id.c18:
                if (checked) {
                    ansArr[63] = 0;
                    ansArr[68] = 0;
                    ansArr[73] = 1;
                    ansArr[78] = 0;
                    break;
                }
            case R.id.d18:
                if (checked){
                    ansArr[63]=0;
                    ansArr[68]=0;
                    ansArr[73]=0;
                    ansArr[78]=1;
                    break;
                }
            case R.id.a19:
                if (checked) {
                    ansArr[64] = 1;
                    ansArr[69] = 0;
                    ansArr[74] = 0;
                    ansArr[79] = 0;
                    break;
                }
            case R.id.b19:
                if (checked){
                    ansArr[64]=0;
                    ansArr[69]=1;
                    ansArr[74]=0;
                    ansArr[79]=0;
                    break;
                }
            case R.id.c19:
                if (checked) {
                    ansArr[64] = 0;
                    ansArr[69] = 0;
                    ansArr[74] = 1;
                    ansArr[79] = 0;
                    break;
                }
            case R.id.d19:
                if (checked){
                    ansArr[64]=0;
                    ansArr[69]=0;
                    ansArr[74]=0;
                    ansArr[79]=1;
                    break;
                }
            case R.id.a20:
                if (checked) {
                    ansArr[65] = 1;
                    ansArr[70] = 0;
                    ansArr[75] = 0;
                    ansArr[80] = 0;
                    break;
                }
            case R.id.b20:
                if (checked){
                    ansArr[65]=0;
                    ansArr[70]=1;
                    ansArr[75]=0;
                    ansArr[80]=0;
                    break;
                }
            case R.id.c20:
                if (checked) {
                    ansArr[65] = 0;
                    ansArr[70] = 0;
                    ansArr[75] = 1;
                    ansArr[80] = 0;
                    break;
                }
            case R.id.d20:
                if (checked){
                    ansArr[65]=0;
                    ansArr[70]=0;
                    ansArr[75]=0;
                    ansArr[80]=1;
                    break;
                }
        }
    }
}
