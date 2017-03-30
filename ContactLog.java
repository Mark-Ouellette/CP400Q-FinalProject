package ca.wlu.markouellette.cp400q_finalproject;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ca.wlu.markouellette.ouel_a3.Contact;

public class ContactLog {
    
    private static ContactLog sContactLog;
    private ArrayList<Contact> mContacts;


    public static ContactLog get(Context context) {
        if (sContactLog == null) {
            sContactLog = new ContactLog(context);
        }
        return sContactLog;
    }

    private ContactLog(Context context) {
        mContacts = new ArrayList<>();
        mContacts.add(new Contact("Barack","Obama","123456789","obama"));
        mContacts.add(new Contact("Donald","Trump","987654321","trump"));
        mContacts.add(new Contact("Kanye","West","4165555678","kanye"));
    }

    public void addContact(Contact c) {
        mContacts.add(c);
    }
    public void removeContact(Contact c){
        mContacts.remove(c);
    }
    public List<Contact> getContacts() {
        return mContacts;
    }



}
