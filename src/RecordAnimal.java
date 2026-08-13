public record RecordAnimal(AnimalName animalName) {
    public RecordAnimal
    {
        if(animalName != null)
        {
            //защита полей через копирование
            animalName = new AnimalName(animalName);
        }

    }
}