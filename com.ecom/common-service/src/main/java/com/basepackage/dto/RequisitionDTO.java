package com.basepackage.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RequisitionDTO {

	
	
	private Long Id; 
	


	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	

	private String requisitionNumber;

    private String name;

    private String requisitionType; // SERVICE or PRODUCT

    private String leadBuyer;

    private LocalDate requisitionDate;

    private String department;

    private String vendor;

    private String shippingAddress;

    private String billingAddress;

    private String paymentTerms;

    private String status;

    private String comments;

    private List<RequisitionItemDTO> items;

	public String getRequisitionNumber() {
		return requisitionNumber;
	}

	public void setRequisitionNumber(String requisitionNumber) {
		this.requisitionNumber = requisitionNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRequisitionType() {
		return requisitionType;
	}

	public void setRequisitionType(String requisitionType) {
		this.requisitionType = requisitionType;
	}

	public String getLeadBuyer() {
		return leadBuyer;
	}

	public void setLeadBuyer(String leadBuyer) {
		this.leadBuyer = leadBuyer;
	}

	public LocalDate getRequisitionDate() {
		return requisitionDate;
	}

	public void setRequisitionDate(LocalDate requisitionDate) {
		this.requisitionDate = requisitionDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<RequisitionItemDTO> getItems() {
		return items;
	}

	public void setItems(List<RequisitionItemDTO> items) {
		this.items = items;
	}


	
	@Override
	public String toString() {
		return "RequisitionDTO [Id=" + Id + ", requisitionNumber=" + requisitionNumber + ", name=" + name
				+ ", requisitionType=" + requisitionType + ", leadBuyer=" + leadBuyer + ", requisitionDate="
				+ requisitionDate + ", department=" + department + ", vendor=" + vendor + ", shippingAddress="
				+ shippingAddress + ", billingAddress=" + billingAddress + ", paymentTerms=" + paymentTerms
				+ ", status=" + status + ", comments=" + comments + ", items=" + items + "]";
	}


    
}
