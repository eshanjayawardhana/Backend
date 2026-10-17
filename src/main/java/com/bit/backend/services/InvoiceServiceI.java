package com.bit.backend.services;

import com.bit.backend.dtos.InvoiceDto;

import java.util.List;

public interface InvoiceServiceI {
    InvoiceDto addInvoice(InvoiceDto invoiceDto);
    List<InvoiceDto> getAllInvoices();
    InvoiceDto getInvoiceById(Integer id);
    InvoiceDto updateInvoice(Integer id, InvoiceDto invoiceDto);
    InvoiceDto deleteInvoice(Integer id);
}
