package ca.wlu.markouellette.cp400q_finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;


public class AddPaymentActivity extends AppCompatActivity {

    private List<Payment> mPayments;
    /*EditText mFirst_Name;
    EditText mLast_Name;
    EditText mPhone_Number;
    String mPhotoID = "ic_launcher";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_add_payment);
        mPayments = PaymentLog.get(this).getPayments();


        Button button= (Button) findViewById(R.id.add_contact_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*mFirst_Name = (EditText)findViewById(R.id.first_name_entry);
                mLast_Name = (EditText)findViewById(R.id.last_name_entry);
                mPhone_Number = (EditText)findViewById(R.id.phone_number_entry);

                mContacts.add(new Contact(mFirst_Name.getText().toString(), mLast_Name.getText().toString(),mPhone_Number.getText().toString(),mPhotoID));
                Intent intent = new Intent (AddPaymentActivity.this,PaymentListActivity.class);
                AddPaymentActivity.this.startActivity(intent);*/
            }
        });
    }
}
