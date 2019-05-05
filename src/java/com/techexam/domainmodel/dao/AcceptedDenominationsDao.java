package com.techexam.domainmodel.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.JAXBException;

public interface AcceptedDenominationsDao {
	public List<BigDecimal> loadAllAcceptedDenominations() throws JAXBException;

	public BigDecimal loadDenomination(BigDecimal denomination);
}
