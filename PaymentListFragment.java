package ca.wlu.markouellette.cp400q_finalproject;

import android.content.Intent;
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

    String mtitleBarName = "Payments";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_list, container, false);

        mPaymentRecyclerView = (RecyclerView) view
                .findViewById(R.id.payment_recycler_view);
        mPaymentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Payment> payments = PaymentLog.get(getActivity()).getPayments();

        mAdapter = new PaymentAdapter(payments);
        mPaymentRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new PaymentTouchHelper(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mPaymentRecyclerView);

        //Add divider lines between each list item
        android.support.v7.widget.DividerItemDecoration dividerItemDecoration
                = new android.support.v7.widget.DividerItemDecoration(mPaymentRecyclerView.getContext(), LinearLayoutManager.VERTICAL);
        mPaymentRecyclerView.addItemDecoration(dividerItemDecoration);

        getActivity().setTitle(mtitleBarName);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //on resume called when we return to this activity from child
    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_payment_list, menu);
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

    //This is where the list items are generated
    private class PaymentHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mLabelView;
        private TextView mAmountView;
        private TextView mPaymentDateView;
        private Payment mPayment;


        public PaymentHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            // TODO Change the layout of the list item to a two row layout so that a "Paid" checkbox can be incorporated.
            mLabelView = (TextView) itemView.findViewById(R.id.payment_item_contact_name);
            mAmountView = (TextView) itemView.findViewById(R.id.payment_item_amount);
            mPaymentDateView = (TextView) itemView.findViewById(R.id.payment_item_payment_date);
        }

        public void bindPayment(Payment payment) {
            mPayment = payment;

            //Set the relevant info for each contact
            mLabelView.setText(mPayment.getLabel());
            mAmountView.setText(String.format(Locale.CANADA, "%.2f", mPayment.getAmount()));

            // TODO Figure out why dates aren't showing up properly (something to do with the long representation?)
            long dateAsLong = mPayment.getPaymentDate();
            if (dateAsLong > 0) {
                Date date = new Date(dateAsLong);
                mPaymentDateView.setText(String.format("Due: %s", new SimpleDateFormat("dd/MM/yyyy").format(date)));
            }
        }

        @Override
        public void onClick(View v) {
            //This is currently directed to the PaymentAddActivity (as an edit payment screen)
            //Once the PaymentActivity (BreakdownActivity) is ready we will direct to that view screen
            //This activity will then have an edit payment button which will pass the PaymentAddActivity
            Intent intent = new Intent(getActivity(), PaymentAddActivity.class);
            Bundle args = new Bundle();
            args.putSerializable(PaymentAddFragment.EXISTING_PAYMENT, mPayment.getId());
            intent.putExtra(PaymentAddFragment.EXISTING_PAYMENT_BUNDLE, args);

            getActivity().startActivity(intent);
        }
    }

    private class PaymentAdapter extends RecyclerView.Adapter<PaymentHolder> {
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

        //TODO remove from PaymentLog not actual array
        public void removePayment(int pos) {
            mPayments.remove(pos);
            this.notifyItemRemoved(pos);
        }
    }

    public class PaymentTouchHelper extends ItemTouchHelper.SimpleCallback {
        private PaymentAdapter mPaymentAdapter;

        public PaymentTouchHelper(PaymentAdapter paymentAdapter) {
            super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            this.mPaymentAdapter = paymentAdapter;
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            // Commented section allows the user to move the items in the list. We disable since it is an ordered list
            //mPaymentAdapter.move(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            mPaymentAdapter.removePayment(viewHolder.getAdapterPosition());
        }
    }
}
