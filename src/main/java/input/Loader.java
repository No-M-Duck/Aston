package input;

import com.github.javafaker.Faker;

import java.util.List;

public interface Loader<T> {
    static Faker getFaker(){
        return new Faker();
    }
    List<T> loadConsole();
    List<T> loadRnd(int count);


}
