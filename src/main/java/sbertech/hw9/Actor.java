package sbertech.hw9;

public class Actor extends Person {
    private final Occupation occupation = Occupation.ACTOR;

    public Actor(String name, String surname, Integer bornDate, Integer deceasedDate) {
        super(name, surname, bornDate, deceasedDate);
    }

    public Actor(String name, String surname, Integer bornDate) {
        super(name, surname, bornDate);
    }

    @Override
    public Occupation occupation() {
        return occupation;
    }
}
