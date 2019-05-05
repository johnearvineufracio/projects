package com.techexam.domainmodel.financialitem;

import java.math.BigDecimal;
import java.util.Currency;

public class MoneyImpl implements Money{
	
	private Currency currency;
	
	private BigDecimal value;

	public MoneyImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "MoneyImpl [currency=" + currency + ", value=" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MoneyImpl other = (MoneyImpl) obj;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public MoneyImpl(Currency currency, BigDecimal value) {
		super();
		this.currency = currency;
		this.value = value;
	}
	
	
	
}
