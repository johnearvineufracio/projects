package com.techexam.domainmodel.product;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "veganChocolates")
public class VeganChocolatesImpl {

	@XmlElement(name = "veganChocolate")
	private List<VeganChocolateImpl> veganChocolates = null;

	public List<VeganChocolateImpl> getVeganChocolate() {
		return veganChocolates;
	}

	public void setVeganChocolate(List<VeganChocolateImpl> veganChocolates) {
		this.veganChocolates = veganChocolates;
	}
}
