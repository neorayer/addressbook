package exam.reece.addressBook.model;

/**
 * Domain Model Object for contact entity.
 * 
 * @author  Rui Zhou
 */
import java.util.UUID;

import javax.persistence.*;

@Entity
public class Contact {

	private String id = UUID.randomUUID().toString();

	private String addressBookId;

	private String name;

	private String mobilePhone;

	private String homePhone;

	private String workPhone;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ADDRESS_BOOK_ID")
	public String getAddressBookId() {
		return addressBookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public void setAddressBookId(String addressBookId) {
		this.addressBookId = addressBookId;
	}

}
