package entity;

public class User {

    private int id;
    private String cardNumber;
    private String         name;
    private String  partment;
    private int           lockFlag;
    private String   userId;
    private String           box;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartment() {
        return partment;
    }

    public void setPartment(String partment) {
        this.partment = partment;
    }

    public int getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(int lockFlag) {
        this.lockFlag = lockFlag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", name='" + name + '\'' +
                ", partment='" + partment + '\'' +
                ", lockFlag=" + lockFlag +
                ", userId='" + userId + '\'' +
                ", box='" + box + '\'' +
                '}';
    }
}
