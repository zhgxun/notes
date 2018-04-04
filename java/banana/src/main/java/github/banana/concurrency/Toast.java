package github.banana.concurrency;

/**
 * 管理土司属性
 */
public class Toast {

    /**
     * 土司的状态, 制作->涂抹黄油->涂抹果酱->制作完毕可供消费
     */
    public enum Status {
        DRY, BUTTERED, JAMMED
    }

    /**
     * 土司默认是制作状态
     */
    private Status status = Status.DRY;

    /**
     * 当前土司的ID
     */
    private final int id;

    public Toast(int id) {
        this.id = id;
    }

    /**
     * 涂抹黄油状态
     */
    public void butter() {
        status = Status.BUTTERED;
    }

    /**
     * 涂抹果酱状态
     */
    public void jam() {
        status = Status.JAMMED;
    }

    /**
     * 土司的当前状态
     *
     * @return 吐司状态
     */
    public Status getStatus() {
        return status;
    }

    /**
     * 土司的ID
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Toast " + getId() + ": " + getStatus();
    }
}
