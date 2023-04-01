package com.masai.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class Billing implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double fixedCharge;
	private int unitsConsumed;
	private final double TAX = 0.025;
	private double adjustment;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean isPaid;

	public Billing() {
		super();
	}

	public Billing(double fixedCharge, int unitsConsumed, double adjustment, LocalDate startDate, LocalDate endDate, boolean isPaid) {
		super();
		this.fixedCharge = fixedCharge;
		this.unitsConsumed = unitsConsumed;
		this.adjustment = adjustment;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isPaid = isPaid;
	}

	public double getFixedCharge() {
		return fixedCharge;
	}

	public void setFixedCharge(double fixedCharge) {
		this.fixedCharge = fixedCharge;
	}

	public int getUnitsConsumed() {
		return unitsConsumed;
	}

	public void setUnitsConsumed(int unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}

	public double getTAX() {
		return TAX;
	}

	public double getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(double adjustment) {
		this.adjustment = adjustment;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
	
	@Override
	public String toString() {
		return "Billing [fixedCharge=" + fixedCharge + ", unitsConsumed=" + unitsConsumed + ", TAX=" + TAX
				+ ", adjustment=" + adjustment + ", startDate=" + startDate + ", endDate=" + endDate + ", isPaid=" + isPaid
				+ "]";
	}
	
}
