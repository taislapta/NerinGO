package lt.macrosoft.neringo;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainActivity extends Activity {

    EditText input1;
    EditText output1;
    EditText percent1;
    EditText pcustom;
    RadioButton pvm;
    RadioButton delta;
    Button calc;

    float vat_c = 0.21f;
    float percent2;
    DecimalFormat df = new DecimalFormat("##.####", new DecimalFormatSymbols(Locale.US));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_layout);

        input1 = (EditText) findViewById(R.id.input1);
        output1 = (EditText) findViewById(R.id.output1);
        output1.setInputType(InputType.TYPE_NULL);
        percent1 = (EditText) findViewById(R.id.percent1);
        percent1.setInputType(InputType.TYPE_NULL);
        pcustom = (EditText) findViewById(R.id.pcustom);
        pvm = (RadioButton) this.findViewById(R.id.vat);
        pvm.setChecked(true);
        delta = (RadioButton) this.findViewById(R.id.percent);

        calc = (Button) this.findViewById(R.id.calc);
    }

    public void onClick(View arg0) {
        if (pvm.isChecked()) {
            calculateVAT();
        }
        if (delta.isChecked()) {
            calculatePercent();
        }
    }

    protected void calculateVAT(){
        float val = Float.valueOf(input1.getText().toString());
        percent2 = (val*vat_c);
        val=percent2+val;
        output1.setText(df.format(val));
        percent1.setText(df.format(percent2));
    }

    protected void calculatePercent(){
        float val = Float.valueOf(input1.getText().toString());
        if (pcustom.getText().toString().isEmpty()) {
            val = val;
            percent2 = 0;
         }
        else {
            float val_2 = Float.valueOf(pcustom.getText().toString());
            percent2 = (val * val_2 / 100);
            val = percent2 + val;
        }
        df.format(val);
        output1.setText(df.format(val));
        percent1.setText(df.format(percent2));
    }
}