package com.telran.contacts.repository;

import com.telran.contacts.dto.Contact;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class MemoryContactRepository implements IContactRepository {

    // Map saves contacts. Key - id, value - contacts.
    Map<Integer, Contact> source = new HashMap<>();
    AtomicInteger currentId = new AtomicInteger();

    @Override
    public void add(Contact contact) {
        contact.setId(currentId.incrementAndGet());
        source.put(currentId.get(), contact);
        // source.put(contact.getId(), contact);
    }

    @Override
    public Contact get(int id) {
        return source.get(id);
    }

    @Override
    public void edit(Contact contact) {
        source.replace(contact.getId(), contact);
    }

    @Override
    public Contact remove(int id) {
        return source.remove(id);
    }

    @Override
    public boolean remove(Contact contact) {
        return false;
    }

    @Override
    public List<Contact> getAll() {
        return source.values()
                .stream()
                .sorted(Comparator.comparingInt(Contact::getId))
                .collect(Collectors.toList());
    }
}
