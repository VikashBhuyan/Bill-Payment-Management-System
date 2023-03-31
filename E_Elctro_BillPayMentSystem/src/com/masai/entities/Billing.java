package com.masai.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class Billing implements Serializable {
	
	private double fixedPrice;
	private int unit;
	private final double TAX = 0.025;
	private double adjustment;
	private LocalDate dt;
	
	public Billing(){
		super();
	}
	
	public Billing(double fixedPrice, int unit, double adjustment, LocalDate dt) {
		super();
		this.fixedPrice = fixedPrice;
		this.unit = unit;
		this.adjustment = adjustment;
		this.dt = dt;
	}

	public double getFixedPrice() {
		return fixedPrice;
	}

	public void setFixedPrice(double fixedPrice) {
		this.fixedPrice = fixedPrice;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public double getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(double adjustment) {
		this.adjustment = adjustment;
	}

	public LocalDate getDt() {
		return dt;
	}

	public void setDt(LocalDate dt) {
		this.dt = dt;
	}

	public double getTAX() {
		return TAX;
	}

	@Override
	public String toString() {
		return String.format("Billing [fixedPrice=%s, unit=%s, TAX=%s, adjustment=%s, dt=%s]", fixedPrice, unit, TAX,
				adjustment, dt);
	}
	
}
