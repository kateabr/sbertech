package sbertech.hw9;

import java.lang.reflect.Array;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Person {
    private final String name;
    private final String surname;
    private final Integer bornDate;
    private final Integer deceasedDate;

    public Person(String name, String surname, Integer bornDate, Integer deceasedDate) {
        this.name = name;
        this.surname = surname;
        this.bornDate = bornDate;
        this.deceasedDate = deceasedDate;
    }

    public Person(String name, String surname, Integer bornDate) {
        this.name = name;
        this.surname = surname;
        this.bornDate = bornDate;
        this.deceasedDate = -1;
    }

    public Integer getBornDate() {
        return bornDate;
    }

    public boolean isDeceased() {
        return deceasedDate != -1;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", initials(), occupation());
    }

    public String initials() {
        Optional<String> names = Arrays.stream(name.split("\s")).map(s->s.charAt(0)).map(ch->ch + ". ").reduce((ch1, ch2)->ch1 + ch2);
        return names.get() + surname;
    }

    public Integer fullYearsLived() {
        if (isDeceased()) return deceasedDate - bornDate + 1;
        return Instant.now().atZone(ZoneId.systemDefault()).getYear() - bornDate + 1;
    }

    public Occupation occupation() {
        return Occupation.values()[0];
    }
}
