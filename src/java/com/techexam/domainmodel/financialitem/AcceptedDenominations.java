package com.techexam.domainmodel.financialitem;

import java.math.BigDecimal;
import java.util.List;

public interface AcceptedDenominations {
	public List<BigDecimal> getDenominations();

	public void getDenominations(List<BigDecimal> denominations);
}
