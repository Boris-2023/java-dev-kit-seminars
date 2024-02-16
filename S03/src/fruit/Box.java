package fruit;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private final List<T> container;

    public Box() {
        this.container = new ArrayList<>();
    }

    void add(T fruit){
        container.add(fruit);
    }

    public T get(int index){
        return container.get(index);
    }

    float getWeight(){
        return (container.get(0).getWeight())*(container.size());
    }

    // type below is simply Box<?> w/o 'extends Fruit' as the Box can
    // anyway get argument of Fruit classes only
    boolean compareByWeight(Box<?> anotherBox){
        return getWeight() == anotherBox.getWeight();
    }

    // limit from beneath - any type of data, which is parent to type T
    void transferTo(Box<? super T> dest){
        dest.container.addAll(container);
        this.container.clear();
    }

    @Override
    public String toString(){
        return container.toString();
    }

}
