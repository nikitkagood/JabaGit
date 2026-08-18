package Homework1;

import java.util.Objects;

public final class Animal {
    private final AnimalName animalName;

    public Animal(String name) {
        animalName = new AnimalName(name);
    }

    public AnimalName getAnimalName()
    {
        return new AnimalName(this.animalName);
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + '{' + "AnimalName=" + '\'' + this.animalName + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(getAnimalName(), animal.getAnimalName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getAnimalName());
    }
}
