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
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.UUID;

public class PaymentAddFragment extends Fragment {
    public static final String EXISTING_PAYMENT = "PASS_PAYMENT";
    public static final String EXISTING_PAYMENT_BUNDLE = "PASS_BUNDLE";
    public static final String EDIT_PAYMENT_CAPTION = "Edit Payment";
    public static final int MAX_CONTRIBUTORS = 9;

    private Payment mExistingPayment;
    private EditText mContactName;
    private EditText mAmount;
    private CheckBox mPayTo;
    private DatePicker mPaymentDate;
    private SeekBar mNumContributors;
    private EditText mDescription;
    private Button mPaymentAddButton;

    public PaymentAddFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (!(args == null)) {
            UUID existingPaymentUUID = (UUID) args.getSerializable(PaymentAddFragment.EXISTING_PAYMENT);
            mExistingPayment = PaymentLog.get(getActivity()).getPayment(existingPaymentUUID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_payment_add, container, false);
        mContactName = (EditText) v.findViewById(R.id.payment_add_contact_name);
        mAmount = (EditText) v.findViewById(R.id.payment_add_amount);
        mPayTo = (CheckBox) v.findViewById(R.id.payment_add_pay_to);
        mPaymentDate = (DatePicker) v.findViewById(R.id.payment_add_payment_date);
        mNumContributors = (SeekBar) v.findViewById(R.id.payment_add_num_contributors);
        mNumContributors.setMax(MAX_CONTRIBUTORS);
        mDescription = (EditText) v.findViewById(R.id.payment_add_description);

        mPaymentAddButton = (Button) v.findViewById(R.id.payment_add_button); // add_payment_button

        // An existing payment item was passed to this fragment, fill out the fields and change the button label
        if (!(mExistingPayment == null)) {
            mContactName.setText(mExistingPayment.getContactName());
            mAmount.setText(Double.toString(mExistingPayment.getAmount()));
            mPayTo.setChecked(mExistingPayment.isPayTo());
            mPaymentDate.setMaxDate(mExistingPayment.getPaymentDate());
            //TODO link this line to the numContributors of the Payment class when it is implemented
            mNumContributors.setProgress(1);
            mDescription.setText(mExistingPayment.getDescription());
            mPaymentAddButton.setText(EDIT_PAYMENT_CAPTION);
        }

        //Add/Edit button clicked
        mPaymentAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contactName = mContactName.getText().toString().trim();
                boolean payTo = mPayTo.isActivated();
                double amount = Double.parseDouble(mAmount.getText().toString());
                long paymentDate = mPaymentDate.getMaxDate();
                String description = mDescription.getText().toString().trim();

                //If key fields are left entry, do not allow the user to proceed.
                if (contactName == "" || amount <= 0) {
                    Toast.makeText(getActivity(), "Please ensure the Contact Name and Amount are filled", Toast.LENGTH_LONG).show();
                } else {
                    //If this is an edit, set payment fields. Otherwise create and add new payment
                    if (!(mExistingPayment == null)) {
                        mExistingPayment.setContactName(contactName);
                        mExistingPayment.setPayTo(payTo);
                        mExistingPayment.setAmount(amount);
                        mExistingPayment.setPaymentDate(paymentDate);
                        mExistingPayment.setDescription(description);
                    } else {
                        PaymentLog paymentLog = PaymentLog.get(getContext());
                        Payment p = new Payment(contactName, payTo, amount, paymentDate, description);
                        paymentLog.addPayment(p);
                    }
                    getActivity().finish();
                }
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
