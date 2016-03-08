package exam.reece.addressBook;

/**
 * The most simplest implementation of BeanFactory interface.
 * It could be replace by Spring BeanFactory or ApplicationContact
 * 
 * @author   Rui Zhou
 * @see     java.lang.Exception
 * @see     exam.reece.addressBook.BeanFactory;
 */
import exam.reece.addressBook.dao.*;
import exam.reece.addressBook.dao.simpleImpl.*;
import exam.reece.addressBook.service.*;

public class SimpleBeanFactory implements BeanFactory {

	public SimpleBeanFactory() {
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getBean(String beanName) throws BeanException {
		switch (beanName) {
		case "AddressBookService":
			return (T) getAddressBookService();
		case "SimpleAddressBookDao":
			return (T) getAddressBookDao();
		case "ContactService":
			return (T) getContactService();
		case "SimpleContactDao":
			return (T) getContactDao();
		default:
			throw new BeanException("Can not get bean by name: " + beanName);
		}
	}
	
	private AddressBookDao getAddressBookDao() {
		return new SimpleAddressBookDao(); 
	}
	
	private AddressBookService getAddressBookService() {
		AddressBookService service = new AddressBookService();
		service.setAddressBookDao(getAddressBookDao());
		return service;
	}
	
	private ContactDao getContactDao() {
		return new SimpleContactDao();
	}
	
	private ContactService getContactService() {
		ContactService service = new ContactService();
		service.setContactDao(getContactDao());
		service.setAddressBookService(getAddressBookService());
		return service;
	}

}
