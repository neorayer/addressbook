package exam.reece.addressBook.model;

/**
 * Domain Model Object for address book entity.
 * 
 * @author  Rui Zhou
 */
import javax.persistence.Entity;
import java.util.UUID;
import javax.persistence.*;

@Entity
public class AddressBook {

	private String id = UUID.randomUUID().toString();

	private String ownerId;

	private String name;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressBook() {

	}

}
