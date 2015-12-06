package com.github.pixelase.contacts.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.github.pixelase.contacts.dataaccess.model.Person;
import com.github.pixelase.contacts.services.common.AbstractServiceTest;

@Transactional
public class PersonServiceTest extends AbstractServiceTest<Person, Integer, PersonService> {

	@Override
	protected Person generateEntity() {
		return new Person(RandomStringUtils.random(MAX_STRING_LENGTH), RandomStringUtils.random(MAX_STRING_LENGTH));
	}

	@Override
	protected Iterable<? extends Person> generateEntities(int entitieCount) {
		List<Person> list = new ArrayList<>();

		for (int i = 0; i < MAX_ENTITIES_COUNT; i++) {
			list.add(generateEntity());
		}

		return list;
	}

	@Override
	protected Integer generateId() {
		return RandomUtils.nextInt(1, MAX_NUMBER);
	}
	
	@Test
	public void findAllByParamsTest() {
		List<Person> persons = new ArrayList<>();
		
		for (int i = 0; i < RandomUtils.nextInt(1, MAX_ENTITIES_COUNT + 1); i++) {
			persons.add(new Person(entity.getFirstName(), entity.getLastName()));
		}
		
		List<Person> saved = service.save(persons);		
		List<Person> found = service.findAll(entity.getFirstName(), entity.getLastName());
		
		Assert.assertEquals(saved, found);
	}
	
	@Test
	public void findOneByParamsTest() {
		Person saved = service.save(entity);
		
		Person found = service.findOne(saved.getId());
		
		Assert.assertEquals(saved, found);
	}
}
