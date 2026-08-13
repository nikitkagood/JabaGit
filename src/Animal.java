class AnimalName {
    protected String name = "default name";

    AnimalName() {}

    AnimalName(String str) {
        setName(str);
    }

    AnimalName(AnimalName animalNameOther) {
        if(animalNameOther != null)
        {
            this.name = animalNameOther.name;
        }
    }

    void setName(String newName) {
        this.name = newName;
    }

    void sayName()
    {
        IO.println(String.format("My name is: %s", this.name));
    }
}

final public class Animal {
    Animal(String name) {
        animalName = new AnimalName();
        animalName.setName(name);
    }

    public boolean equals(Object obj) {

        return true;
    }

    final AnimalName animalName;
}
