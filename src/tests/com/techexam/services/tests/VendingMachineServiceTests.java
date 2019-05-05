package com.techexam.services.tests;

import com.techexam.domainmodel.dao.AcceptedDenominationsDao;
import com.techexam.domainmodel.dao.VeganChocolatesDao;
import com.techexam.domainmodel.product.VeganChocolate;
import com.techexam.domainmodel.product.VeganChocolateImpl;
import com.techexam.services.VendingMachineServices;
import com.techexam.services.VendingMachineServicesImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class VendingMachineServiceTests {

	private VendingMachineServices service;

	@Mock
	AcceptedDenominationsDao denomDao;

	@Mock
	VeganChocolatesDao chocoDao;

	@Before
	public void setup() {
		service = new VendingMachineServicesImpl();
	}

	@Test
	public void testValidateCorrectDenomination() {

		Mockito.when(denomDao.loadDenomination(BigDecimal.ONE)).thenReturn(BigDecimal.ONE);

		Assert.assertNotNull(service.validateDenomination("1"));
	}
	
	@Test
	public void testValidateIncorrectDenomination() {

		Mockito.when(denomDao.loadDenomination(BigDecimal.ONE)).thenReturn(null);

		Assert.assertNull(service.validateDenomination("1"));
	}
	
	@Test
	public void testGetValidChocolateForCorrectAmount(){
		Mockito.when(chocoDao.loadValidVeganChocolate(BigDecimal.ONE)).thenReturn(returnNoValueChocolateObjects());

		Assert.assertNull(service.getAvailableChocolateForAmountEntered(BigDecimal.ONE));
	}
	
	@Test
	public void testGetValidChocolateForIncorrectAmount(){
		Mockito.when(chocoDao.loadValidVeganChocolate(BigDecimal.ONE)).thenReturn(new ArrayList<VeganChocolate>());

		Assert.assertNull(service.getAvailableChocolateForAmountEntered(BigDecimal.ONE));
	}
	
	@Test
	public void testGetTotalCoinsEntered(){
		List<String> enteredCoins=Arrays.asList("2","2.5","1");
		
		Assert.assertEquals(new BigDecimal("5.5"), service.getTotalCoinsEntered(enteredCoins));
	}
	
	@Test
	public void testCalculateChange(){
		Assert.assertEquals(BigDecimal.ONE, service.calculateChange(new BigDecimal(2), new BigDecimal(1)));
	}

	private List<VeganChocolate> returnNoValueChocolateObjects(){
		List<VeganChocolate> veganChocolates=new ArrayList<>();
		
		veganChocolates.add(new VeganChocolateImpl());
		
		return veganChocolates;
	}
	
}
