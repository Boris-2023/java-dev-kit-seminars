package fruit;

public class Apple implements Fruit{
    private static final float WEIGHT = 1.0F;

    @Override
    public float getWeight() {
        return WEIGHT;
    }

    @Override
    public String toString(){
        return "Apple";
    }
}
