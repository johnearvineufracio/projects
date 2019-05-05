package com.techexam.domainmodel.dao;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.techexam.domainmodel.financialitem.AcceptedDenominationsImpl;

public class AcceptedDenominationsDaoImpl implements AcceptedDenominationsDao {

	private List<BigDecimal> acceptedDenominations;

	public AcceptedDenominationsDaoImpl() {
		try {
			acceptedDenominations = loadAllAcceptedDenominations();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public List<BigDecimal> loadAllAcceptedDenominations() throws JAXBException {
		List<BigDecimal> acceptedDenominations = new ArrayList<>();
		JAXBContext jaxbContext2 = null;
		jaxbContext2 = JAXBContext.newInstance(AcceptedDenominationsImpl.class);
		Unmarshaller jaxbUnmarshaller2 = null;
		jaxbUnmarshaller2 = jaxbContext2.createUnmarshaller();

		AcceptedDenominationsImpl denoms = (AcceptedDenominationsImpl) jaxbUnmarshaller2
				.unmarshal(new File("resources/acceptedDenominations.xml"));

		for (BigDecimal denom : denoms.getDenominations()) {
			acceptedDenominations.add(denom);
		}
		return acceptedDenominations;
	}

	public BigDecimal loadDenomination(BigDecimal denomination) {
		for (BigDecimal acceptedDenomination : acceptedDenominations) {
			if (acceptedDenomination.compareTo(denomination) == 0) {
				return acceptedDenomination;
			}
		}

		return null;
	}
}
