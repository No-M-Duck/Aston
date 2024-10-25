package org.example.input;

import com.github.javafaker.Faker;

import java.util.*;

public interface Loader<T> {

    static Faker getFaker(){
        return new Faker();
    }

    List<T> loadRnd(int count);

}
