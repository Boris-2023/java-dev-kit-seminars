package task01b_dead_lock_friends;

public class Friend {
    private String name;

    public Friend(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // if remove synchronized - all works fine till the end
    public synchronized void bow(Friend friend){
        System.out.println(name + ": " + friend.getName() + " поклонился мне");
        friend.bowBack(this);
    }

    // if remove synchronized - all works fine till the end
    private synchronized void bowBack(Friend friend){
        System.out.println(name + " кланяется в ответ " + friend.getName());
    }
}
