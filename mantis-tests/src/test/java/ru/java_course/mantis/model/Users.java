package ru.java_course.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UserData> {

    private Set<UserData> delegate;

    public Users() {
        this.delegate = new HashSet<>();
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }

}
