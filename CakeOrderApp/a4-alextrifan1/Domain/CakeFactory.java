package Domain;

public class CakeFactory implements IEntityFactory<Cake>{
    @Override
    public Cake createEnity(String line) {
        int id = Integer.parseInt(line.split(",")[0]);
        String cakeType = line.split(",")[1];
        return new Cake(id, cakeType);
    }

    @Override
    public String toString(Cake entity) {
        return entity.toString();
    }
}