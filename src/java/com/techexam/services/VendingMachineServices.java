package com.techexam.services;

import java.math.BigDecimal;
import java.util.List;

import com.techexam.domainmodel.product.VeganChocolate;

public interface VendingMachineServices {

	public boolean validateDenomination(String denomination);

	public BigDecimal getTotalCoinsEntered(List<String> enteredCoins);

	public List<VeganChocolate> getAvailableChocolateForAmountEntered(BigDecimal amount);
	
	public BigDecimal calculateChange(BigDecimal amountEntered, BigDecimal productValue);
}
