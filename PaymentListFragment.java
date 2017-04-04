package ca.wlu.markouellette.cp400q_finalproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mark on 2017-02-12.
 */

public class PaymentListFragment extends Fragment {

    private RecyclerView mPaymentRecyclerView;
    private PaymentAdapter mAdapter;
    public static String EXTRA_Label = "pass_reminder_label";
    String mtitleBarName = "Payments";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_list, container, false);

        mPaymentRecyclerView = (RecyclerView) view
                .findViewById(R.id.payment_recycler_view);
        mPaymentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        getActivity().setTitle(mtitleBarName);
        return view;
    }

    //on resume called when we return to this activity from child
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_payment:
                Intent intent = new Intent(getActivity(), PaymentAddActivity.class);
                getActivity().startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                mAdapter.removePayment(viewHolder.getAdapterPosition());
        }

    };



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_payment_list, menu);
    }

    private void updateUI() {
        PaymentLog paymentLog = PaymentLog.get(getActivity());
        List<Payment> payments = paymentLog.getPayments();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mPaymentRecyclerView);
        mPaymentRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        mAdapter = new PaymentAdapter(payments);
        mPaymentRecyclerView.setAdapter(mAdapter);

    }

    private class PaymentHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mContactNameView;
        private TextView mAmountView;
        private TextView mPaymentDateView;
        private Payment mPayment;


        public PaymentHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mContactNameView = (TextView) itemView.findViewById(R.id.payment_item_contact_name);
            mAmountView = (TextView) itemView.findViewById(R.id.payment_item_amount);
            mPaymentDateView = (TextView) itemView.findViewById(R.id.payment_item_payment_date);
        }

        public void bindPayment(Payment payment) {
            mPayment = payment;
            mContactNameView.setText(mPayment.getContactName());
            mAmountView.setText(String.format(Locale.CANADA, "$%.2f", mPayment.getAmount()));
            long dateAsLong = mPayment.getPaymentDate();
            if (dateAsLong > 0) {
                Date date = new Date(dateAsLong);
                mPaymentDateView.setText(String.format("Due: %s", new SimpleDateFormat("dd/MM/yyyy").format(date)));
            }
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(),Breakdown.class);
            /*String name = mContact.getFirstName() + " " + mContact.getLastName();
            String phone = mContact.getPhoneNumber();
            String photoid = mContact.getPhoto();
            intent.putExtra(EXTRA_NAME,name);
            intent.putExtra(EXTRA_PHONE,phone);
            intent.putExtra(EXTRA_PHOTO,photoid);*/
            getActivity().startActivity(intent);
        }
    }

    private class PaymentAdapter extends RecyclerView.Adapter<PaymentHolder>{

        private List<Payment> mPayments;


        public PaymentAdapter(List<Payment> payments) {
            mPayments = payments;
        }

        @Override
        public PaymentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            View view = layoutInflater.inflate(R.layout.list_item_payment, parent, false);
            return new PaymentHolder(view);
        }

        @Override
        public void onBindViewHolder(PaymentHolder holder, int position) {
            Payment payment = mPayments.get(position);
            holder.bindPayment(payment);
        }

        @Override
        public int getItemCount() {
            return mPayments.size();
        }


        public void removePayment(int pos){
            mPayments.remove(pos);
            this.notifyItemRemoved(pos);
        }

    }

    public class DividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public DividerItemDecoration(Context context) {
            mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int numChildren = parent.getChildCount();
            for (int i = 0; i < numChildren; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

    }
}
