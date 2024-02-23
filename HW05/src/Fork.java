public class Fork {
    private volatile boolean isFree = true;
    private static int forkId = 0;

    private final int id;

    public Fork() {
        id = ++forkId;
    }

    public boolean isFree() {
        return isFree;
    }

    public void release() {
        isFree = true;
    }

    public void engage() {
        isFree = false;
    }

    public int getId() {
        return id;
    }

}
