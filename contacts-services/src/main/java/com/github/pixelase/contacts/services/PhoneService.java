package com.github.pixelase.contacts.services;

import java.util.List;

import com.github.pixelase.contacts.dataaccess.model.Person;
import com.github.pixelase.contacts.dataaccess.model.Phone;
import com.github.pixelase.contacts.services.common.GenericService;

public interface PhoneService extends GenericService<Phone, Integer> {
	Phone delete(String number);

	List<Phone> deleteAll(Person person);

	List<Phone> findAllByPartialMatching(String number);

	Phone findOne(String number);

	Phone findOne(Person person);
}
