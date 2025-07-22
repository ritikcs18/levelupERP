package com.levelup.erp.vendor.dto;

import com.levelup.erp.vendor.model.BusinessType;
import com.levelup.erp.vendor.model.Vendor;
import com.levelup.erp.vendor.model.VendorBankAccount;
import com.levelup.erp.vendor.repository.BusinessTypeRepository;
import com.levelup.erp.vendor.repository.VendorBankAccountRepository;
import com.levelup.erp.vendor.repository.VendorRepository;


public class VendorMapper {

    private final VendorRepository vendorRepository;


    public VendorMapper (VendorRepository vendorRepository,
                             VendorBankAccountRepository bankAccountRepository,
                             BusinessTypeRepository businessTypeRepository) {
        this.vendorRepository = vendorRepository;
    }


    public static VendorDTO toDTO(Vendor v) {
        VendorDTO dto = new VendorDTO();

        dto.setId(v.getId());
        dto.setVendorCode(v.getVendorCode());
        dto.setName(v.getName());
        dto.setAddress(v.getAddress());
        dto.setCity(v.getCity());
        dto.setRegion(v.getRegion());
        dto.setCountry(v.getCountry());
        dto.setPincode(v.getPincode());
        dto.setGovtId(v.getGovtId());
        dto.setGstNumber(v.getGstNumber());
        dto.setEmail(v.getEmail());
        dto.setActive(v.isActive());
        dto.setPaymentCurrency(v.getPaymentCurrency());
        dto.setPurchasingGroup(v.getPurchasingGroup());
        dto.setOnboardingDate(v.getOnboardingDate());
        dto.setExitDate(v.getExitDate());
        dto.setIsdCode(v.getIsdCode());
        dto.setContactNumber(v.getContactNumber());

        if (v.getBusinessType() != null) {
            dto.setBusinessTypeCode(v.getBusinessType().getCode());
        }

        if (v.getBankAccount() != null) {
            dto.setAccountNumber(v.getBankAccount().getAccountNumber());
            dto.setIfscCode(v.getBankAccount().getIfscCode());
        }

        return dto;
    }

    public static Vendor toEntity(VendorDTO dto) {
        if (dto == null) return null;

        Vendor vendor = new Vendor();
        vendor.setId(dto.getId());
        vendor.setVendorCode(dto.getVendorCode());
        vendor.setName(dto.getName());
        vendor.setAddress(dto.getAddress());
        vendor.setCity(dto.getCity());
        vendor.setRegion(dto.getRegion());
        vendor.setCountry(dto.getCountry());
        vendor.setPincode(dto.getPincode());
        vendor.setGovtId(dto.getGovtId());
        vendor.setGstNumber(dto.getGstNumber());
        vendor.setEmail(dto.getEmail());
        vendor.setActive(dto.isActive());
        vendor.setPaymentCurrency(dto.getPaymentCurrency());
        vendor.setPurchasingGroup(dto.getPurchasingGroup());
        vendor.setOnboardingDate(dto.getOnboardingDate());
        vendor.setExitDate(dto.getExitDate());
        vendor.setIsdCode(dto.getIsdCode());
        vendor.setContactNumber(dto.getContactNumber());


        VendorBankAccount bankAccount = new VendorBankAccount();
        bankAccount.setAccountNumber(dto.getAccountNumber());
        bankAccount.setIfscCode(dto.getIfscCode());

        // set the back reference and attach
        bankAccount.setVendor(vendor);
        vendor.setBankAccount(bankAccount);

        BusinessType businessType = new BusinessType();
        businessType.setCode(dto.getBusinessTypeCode());
        vendor.setBusinessType(businessType);

        return vendor;
    }

    private static VendorBankAccount toBankEntity(VendorDTO dto) {
        VendorBankAccount bank = new VendorBankAccount();
        bank.setAccountNumber(dto.getAccountNumber());
        bank.setIfscCode(dto.getIfscCode());
        return bank;
    }


}
