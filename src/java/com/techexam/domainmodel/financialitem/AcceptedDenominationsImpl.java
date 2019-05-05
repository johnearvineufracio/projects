package com.techexam.domainmodel.financialitem;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "denominations")
public class AcceptedDenominationsImpl {
	@XmlElement(name = "denomination")
	private List<BigDecimal> denominations = null;

	public List<BigDecimal> getDenominations() {
		return denominations;
	}

	public void getDenominations(List<BigDecimal> denominations) {
		this.denominations = denominations;
	}
}
