package ca.wlu.markouellette.cp400q_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.List;

//import android.widget.EditText;


public class AddPaymentActivity extends AppCompatActivity {

    private List<Payment> mPayments;
    private EditText mLabel;
    private EditText mBill_Amount;
    private CalendarView mDue_Date;
    private NumberPicker mNumPeople;
    private CalendarView mReminderDate;
    private EditText mAmountPaid;
    private CheckBox mPaid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_add_payment);
        mPayments = PaymentLog.get(this).getPayments();


        Button button= (Button) findViewById(R.id.add_payment_button); // add_payment_button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLabel = (EditText)findViewById(R.id.label_entry);
                mBill_Amount = (EditText)findViewById(R.id.payment_amount_entry);
                mDue_Date = (CalendarView)findViewById(R.id.payment_due_date_entry);
                mNumPeople = (NumberPicker)findViewById(R.id.group_size_entry);
                mNumPeople.setMaxValue(10);
                mNumPeople.setMinValue(1);
                String[] nums = {"1","2","3","4","5","6","7","8","9","10"};
                mNumPeople.setDisplayedValues(nums);
                mReminderDate = (CalendarView) findViewById(R.id.reminder_date_entry);
                //mAmountpaid = (EditText);
                //mPaid = (CheckBox) findViewById(R.id.is_paid);

                mPayments.add(new Payment(mLabel.getText().toString(),mDue_Date.getDate(),mNumPeople.getValue(),mReminderDate.getDate()));
                Intent intent = new Intent (AddPaymentActivity.this,PaymentListActivity.class);
                AddPaymentActivity.this.startActivity(intent);
            }
        });
    }
}
