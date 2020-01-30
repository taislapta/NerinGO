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
        //check if there are any value in Eur EditText, if yes set 0
        if (input1.getText().toString().isEmpty()) {
            float val = 0;
            percent2 = 0;
            output1.setText(df.format(val));
            percent1.setText(df.format(percent2));
        }
        else {
            float val = Float.valueOf(input1.getText().toString());
            percent2 = (val * vat_c);
            val = percent2 + val;
            output1.setText(df.format(val));
            percent1.setText(df.format(percent2));

        }

    }

    protected void calculatePercent(){
        //check if Eur EditTExt is empty, then check if percent is empty as well
        if (input1.getText().toString().isEmpty()) {
            if (pcustom.getText().toString().isEmpty()) {
                float val = 0;
                percent2 = 0;
                df.format(val);
                output1.setText(df.format(val));
                percent1.setText(df.format(percent2));
            }
            else {
                //Percent EDIT text is not empty anyway result 0 as input is empty
                float val = 0;
                percent2 = 0;
                df.format(val);
                output1.setText(df.format(val));
            }
         }
        else {
            //check ifpercent custom EditText is empty  but input Eur is not
            if (pcustom.getText().toString().isEmpty()) {
                float val = Float.valueOf(input1.getText().toString());
                percent2 = 0;
                df.format(val);
                output1.setText(df.format(val));
                percent1.setText(df.format(percent2));
            }
            else {
                // proceed with both not empty
                float val = Float.valueOf(input1.getText().toString());
                float val_2 = Float.valueOf(pcustom.getText().toString());
                percent2 = (val * val_2 / 100);
                val = percent2 + val;
                df.format(val);
                output1.setText(df.format(val));
                percent1.setText(df.format(percent2));
            }
        }

    }
}