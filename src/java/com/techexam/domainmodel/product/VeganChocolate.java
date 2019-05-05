package com.techexam.domainmodel.product;

import java.math.BigDecimal;

public interface VeganChocolate {

	public String getDescription();

	public void setDescription(String description);

	public BigDecimal getValue();

	public void setValue(BigDecimal value);

	public String getSku();

	public void setSku(String sku);

}
