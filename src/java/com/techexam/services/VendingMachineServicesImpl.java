package com.techexam.services;

import java.math.BigDecimal;
import java.util.List;

import com.techexam.domainmodel.dao.AcceptedDenominationsDao;
import com.techexam.domainmodel.dao.AcceptedDenominationsDaoImpl;
import com.techexam.domainmodel.dao.VeganChocolatesDao;
import com.techexam.domainmodel.dao.VeganChocolatesDaoImpl;
import com.techexam.domainmodel.product.VeganChocolate;

public class VendingMachineServicesImpl implements VendingMachineServices {
	private List<VeganChocolate> chocolates;

	private AcceptedDenominationsDao denomDao = new AcceptedDenominationsDaoImpl();

	private VeganChocolatesDao chocolateDao = new VeganChocolatesDaoImpl();

	public List<VeganChocolate> getAvailableChocolates() {
		return chocolates;
	}

	public boolean validateDenomination(String denomination) {
		BigDecimal denom = new BigDecimal(denomination);

		return denomDao.loadDenomination(denom) != null;
	}

	public List<VeganChocolate> getAvailableChocolateForAmountEntered(BigDecimal amount) {
		return chocolateDao.loadValidVeganChocolate(amount);
	}

	public BigDecimal getTotalCoinsEntered(List<String> enteredCoins) {
		BigDecimal totalAmount = BigDecimal.ZERO;

		for (String enteredCoin : enteredCoins) {
			totalAmount = totalAmount.add(new BigDecimal(enteredCoin));
		}

		return totalAmount;
	}

	public BigDecimal calculateChange(BigDecimal amountEntered, BigDecimal productValue) {
		return amountEntered.subtract(productValue);
	}

}
