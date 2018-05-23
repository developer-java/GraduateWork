package android.gw.com.remotecontroller.models;

public class Item {

    private String number;
    private boolean status;
    private TechType type;
    private boolean isConnected;

    public Item(String number, boolean status, TechType type) {
        this.number = number;
        this.status = status;
        this.type = type;
    }

    public Item() {
    }

    public boolean isStatus() {
        return status;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public TechType getType() {
        return type;
    }

    public void setType(TechType type) {
        this.type = type;
    }
}
