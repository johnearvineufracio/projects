package com.techexam.domainmodel.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.techexam.domainmodel.product.VeganChocolate;

public interface VeganChocolatesDao {
	public List<VeganChocolate> loadAllVeganChocolates() throws JAXBException;

	public List<VeganChocolate> loadValidVeganChocolate(BigDecimal amount);
}
