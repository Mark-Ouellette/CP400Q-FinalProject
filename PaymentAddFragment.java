package ca.wlu.markouellette.cp400q_finalproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;

public class PaymentAddFragment extends Fragment {
    public final String EXISTING_PAYMENT = "PASS_PAYMENT";
    public final String EXISTING_PAYMENT_BUNDLE = "PASS_BUNDLE";

    private EditText mContactName;
    private EditText mAmount;
    private CheckBox mPayTo;
    private DatePicker mPaymentDate;
    private NumberPicker mNumContributors;
    private final String[] NUMS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private EditText mDescription;
    private Button mPaymentAddButton;

    public PaymentAddFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_payment_add, container, false);
        mContactName = (EditText) v.findViewById(R.id.payment_add_contact_name);
        mAmount = (EditText) v.findViewById(R.id.payment_add_amount);
        mPayTo = (CheckBox) v.findViewById(R.id.payment_add_pay_to);
        mPaymentDate = (DatePicker) v.findViewById(R.id.payment_add_payment_date);
        mNumContributors = (NumberPicker) v.findViewById(R.id.payment_add_num_contributors);
        mNumContributors.setMaxValue(10);
        mNumContributors.setMinValue(1);
        mNumContributors.setDisplayedValues(NUMS);
        mDescription = (EditText) v.findViewById(R.id.payment_add_description);

        mPaymentAddButton = (Button) v.findViewById(R.id.payment_add_button); // add_payment_button
        mPaymentAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaymentLog paymentLog = PaymentLog.get(getContext());

                Payment p = new Payment(mContactName.getText().toString(),
                        mPayTo.isActivated(),
                        Double.parseDouble(mAmount.getText().toString()),
                        mPaymentDate.getMaxDate(),
                        mDescription.getText().toString());
                paymentLog.addPayment(p);
                getActivity().finish();
            }
        });

        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
