package Homework1;

import java.util.Objects;

public class AnimalName {
    protected String name = "default name";

    AnimalName() {}

    public AnimalName(String str) {
        setName(str);
    }

    AnimalName(AnimalName animalNameOther) {
        if(animalNameOther != null)
        {
            this.name = animalNameOther.name;
        }
    }

    @Override
    public String toString() {
        return name;
    }

    void setName(String newName) {
        this.name = newName;
    }

    public void sayName()
    {
        IO.println(String.format("My name is: %s", this.name));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AnimalName that = (AnimalName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}