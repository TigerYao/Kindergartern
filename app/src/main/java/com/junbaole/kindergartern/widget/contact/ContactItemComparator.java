package com.junbaole.kindergartern.widget.contact;

import java.util.Comparator;

public class ContactItemComparator implements Comparator<ContactListView.ContactItemInterface> {

	@Override
	public int compare(ContactListView.ContactItemInterface lhs, ContactListView.ContactItemInterface rhs) {
		if(lhs.getItemForIndex() == null || rhs.getItemForIndex() == null)
			return -1;
		
		
		return(lhs.getItemForIndex().compareTo(rhs.getItemForIndex() ) );
		
	}

}