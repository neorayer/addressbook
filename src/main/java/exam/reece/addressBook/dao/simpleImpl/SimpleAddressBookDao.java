package exam.reece.addressBook.dao.simpleImpl;

/**
 * The most simple implementation to simulate a persistence store. 
 * 
 * 
 * @author  Rui Zhou
 * @see 	exam.reece.addressBook.dao.AddressBookDao
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import exam.reece.addressBook.*;
import exam.reece.addressBook.dao.*;
import exam.reece.addressBook.dao.exception.DaoException;
import exam.reece.addressBook.dao.exception.NotExistsException;
import exam.reece.addressBook.model.*;


public class SimpleAddressBookDao implements AddressBookDao {

	/**
	 * A static map to simulator of persistence store for AddressBook. key is
	 * ownerId.
	 */
	private static Map<String, List<AddressBook>> userAddressBooksMap = new ConcurrentHashMap<String, List<AddressBook>>();

	@Override
	public List<AddressBook> findByOwnerId(String ownerId) {
		List<AddressBook> books = userAddressBooksMap.get(ownerId);
		return books == null ? new ArrayList<AddressBook>() : books;
	}

	@Override
	public AddressBook create(AddressBook addrBook) throws DaoException {
		String ownerId = addrBook.getOwnerId();
		if (ownerId == null)
			throw new DaoException("ownerId of is required when creating addressBook");
		List<AddressBook> books = userAddressBooksMap.get(ownerId);
		if (books == null) {
			books = new ArrayList<AddressBook>();
			userAddressBooksMap.put(ownerId, books);
		}
		books.add(addrBook);
		return addrBook;
	}

	@Override
	public void clear() {
		userAddressBooksMap.clear();
	}

	@Override
	public AddressBook read(String ownerId, String id) throws NotExistsException {
		List<AddressBook> books = userAddressBooksMap.get(ownerId);
		if (books == null)
			throw new NotExistsException();
		for (AddressBook ab : books) {
			if (ab.getId().equals(id))
				return ab;
		}
		throw new NotExistsException();
	}

	@Override
	public void updateName(String ownerId, String addrBookId, String newName) throws DaoException {
		AddressBook ab = read(ownerId, addrBookId);
		ab.setName(newName);
	}

	@Override
	public void delete(String ownerId, String addrBookId) throws DaoException {
		List<AddressBook> addrBooks = findByOwnerId(ownerId);
		for (AddressBook ab : addrBooks.toArray(new AddressBook[0])) {
			if (ab.getId().equals(addrBookId)) {
				addrBooks.remove(ab);
			}
		}
	}

}
