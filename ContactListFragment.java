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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mark on 2017-02-12.
 */

public class ContactListFragment extends Fragment {

    private RecyclerView mContactRecyclerView;
    private ContactAdapter mAdapter;
    private RecyclerView.ItemDecoration mDecoration;
    public static String EXTRA_PHONE = "pass_phone_number";
    public static String EXTRA_NAME = "pass_name";
    public static String EXTRA_PHOTO = "pass_photo";
    String mtitleBarName = "Contacts";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        mContactRecyclerView = (RecyclerView) view
                .findViewById(R.id.contact_recycler_view);
        mContactRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
            case R.id.menu_item_new_contact:
                Intent intent = new Intent(getActivity(),AddContactActivity.class);
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
                mAdapter.removeContact(viewHolder.getAdapterPosition());
        }

    };



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_contact_list, menu);
    }

    private void updateUI() {
        ContactLog contactLog = ContactLog.get(getActivity());
        List<Contact> contacts = contactLog.getContacts();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mContactRecyclerView);
        mContactRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        mAdapter = new ContactAdapter(contacts);
        mContactRecyclerView.setAdapter(mAdapter);

    }

    private class ContactHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mNameView;
        private ImageView mPhotoView;
        private Contact mContact;


        public ContactHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNameView = (TextView) itemView.findViewById(R.id.list_item_contact_name);
            mPhotoView = (ImageView) itemView.findViewById(R.id.contact_photo_view);
        }

        public void bindContact(Contact contact) {
            mContact = contact;
            mNameView.setText(mContact.getFirstName() + " " + mContact.getLastName());
            int resID = getResources().getIdentifier(mContact.getPhoto(),"drawable",this.getClass().getPackage().getName());
            mPhotoView.setImageResource(resID);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(),ContactChild.class);
            String name = mContact.getFirstName() + " " + mContact.getLastName();
            String phone = mContact.getPhoneNumber();
            String photoid = mContact.getPhoto();
            intent.putExtra(EXTRA_NAME,name);
            intent.putExtra(EXTRA_PHONE,phone);
            intent.putExtra(EXTRA_PHOTO,photoid);
            getActivity().startActivity(intent);
        }
    }

    private class ContactAdapter extends RecyclerView.Adapter<ContactHolder>{

        private List<Contact> mContacts;


        public ContactAdapter(List<Contact> contacts) {
            mContacts = contacts;
        }

        @Override
        public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            View view = layoutInflater.inflate(R.layout.list_item_contact, parent, false);
            return new ContactHolder(view);
        }

        @Override
        public void onBindViewHolder(ContactHolder holder, int position) {
            Contact contact = mContacts.get(position);
            holder.bindContact(contact);
        }

        @Override
        public int getItemCount() {
            return mContacts.size();
        }


        public void removeContact(int pos){
            mContacts.remove(pos);
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
