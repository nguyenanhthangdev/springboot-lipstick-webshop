package com.example.webshopcosmetics.service.contact;

import com.example.webshopcosmetics.exception.AboutException;
import com.example.webshopcosmetics.exception.ContactException;
import com.example.webshopcosmetics.model.Contact;
import com.example.webshopcosmetics.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{
    @Autowired private ContactRepository contactRepository;

    @Override
    public Contact saveContact(Contact contact) {
        try {
            return contactRepository.save(contact);
        } catch (Exception e) {
            throw new ContactException("Gửi tin nhắn thất bại", e);
        }
    }

    @Override
    public List<Contact> getAllContact() {
        try {
            return contactRepository.findAllByOrderByCreatedAtDesc();
        } catch (Exception e) {
            throw new ContactException("Thao tác thất bại", e);
        }
    }

    @Override
    public Contact getOneContact(Long id) {
        try {
            return contactRepository.getOne(id);
        } catch (Exception e) {
            throw new ContactException("Xem chi tiết ý kiến của khánh hàng thất bại", e);
        }
    }
}
