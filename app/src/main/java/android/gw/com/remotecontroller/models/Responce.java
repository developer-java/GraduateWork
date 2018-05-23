package android.gw.com.remotecontroller.models;

public class Responce {

    private Greeting info;
    private boolean[] ports;
    private boolean[] status;

    public boolean[] getStatus() {
        return status;
    }

    public void setStatus(boolean[] status) {
        this.status = status;
    }

    public Greeting getInfo() {
        return info;
    }

    public void setInfo(Greeting info) {
        this.info = info;
    }

    public boolean[] getPorts() {
        return ports;
    }

    public void setPorts(boolean[] ports) {
        this.ports = ports;
    }
}
