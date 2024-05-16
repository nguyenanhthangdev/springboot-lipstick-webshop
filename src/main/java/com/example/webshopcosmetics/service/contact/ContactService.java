package com.example.webshopcosmetics.service.contact;

import com.example.webshopcosmetics.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {
    public Contact saveContact(Contact contact);

    public List<Contact> getAllContact();

    public Contact getOneContact(Long id);
}
