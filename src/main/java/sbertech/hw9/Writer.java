package sbertech.hw9;

public class Writer extends Person{
    private final Occupation occupation = Occupation.WRITER;

    public Writer(String name, String surname, Integer bornDate, Integer deceasedDate) {
        super(name, surname, bornDate, deceasedDate);
    }

    public Writer(String name, String surname, Integer bornDate) {
        super(name, surname, bornDate);
    }

    @Override
    public Occupation occupation() {
        return occupation;
    }
}
