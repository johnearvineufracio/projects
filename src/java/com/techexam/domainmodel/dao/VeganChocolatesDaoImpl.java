package com.techexam.domainmodel.dao;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.techexam.domainmodel.product.VeganChocolate;
import com.techexam.domainmodel.product.VeganChocolateImpl;
import com.techexam.domainmodel.product.VeganChocolatesImpl;

public class VeganChocolatesDaoImpl implements VeganChocolatesDao {

	public List<VeganChocolate> loadAllVeganChocolates() throws JAXBException {

		List<VeganChocolate> veganChocolates = new ArrayList<VeganChocolate>();
		JAXBContext jaxbContext = JAXBContext.newInstance(VeganChocolatesImpl.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		VeganChocolatesImpl emps = (VeganChocolatesImpl) jaxbUnmarshaller
				.unmarshal(new File("resources/productDetails.xml"));

		for (VeganChocolateImpl veganChocolateImpl : emps.getVeganChocolate()) {
			veganChocolates.add(veganChocolateImpl);
		}

		return veganChocolates;
	}

	public List<VeganChocolate> loadValidVeganChocolate(BigDecimal amount) {
		List<VeganChocolate> veganChocolates = new ArrayList<VeganChocolate>();

		try {
			for (VeganChocolate veganChocolate : loadAllVeganChocolates()) {
				if (amount.compareTo(veganChocolate.getValue()) >= 0) {
					veganChocolates.add(veganChocolate);
				}
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return veganChocolates;
	}

}
