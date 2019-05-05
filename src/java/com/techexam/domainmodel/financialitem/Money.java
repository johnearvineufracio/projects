package com.techexam.domainmodel.financialitem;

import java.math.BigDecimal;
import java.util.Currency;

public interface Money {
	public Currency getCurrency();

	public void setCurrency(Currency currency);

	public BigDecimal getValue();

	public void setValue(BigDecimal value);
}
