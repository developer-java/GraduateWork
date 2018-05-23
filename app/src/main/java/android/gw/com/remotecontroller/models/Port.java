package android.gw.com.remotecontroller.models;

public class Port {

    private int status;
    private int techId;

    public Port(int status, int techId) {
        this.status = status;
        this.techId = techId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTechId() {
        return techId;
    }

    public void setTechId(int techId) {
        this.techId = techId;
    }
}
