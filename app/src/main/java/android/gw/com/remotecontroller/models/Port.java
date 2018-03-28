package android.gw.com.remotecontroller.models;

public class Port {

    private int id;
    private Status status;
    private Type type;

    public Type getType() {
        return type;
    }

    public Port(int id, Status status, Type type) {
        this.id = id;
        this.status = status;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public boolean isActive(){
        return Status.OFF == getStatus() ? false : true;
    }

    public enum Status{
        ON("Включено"), OFF("Выключено");
        private String desc;
        private Status(String desc){
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum Type{
        ANALOG("Аналоговый порт"), DIGITAL("Цифровой порт");
        private String desc;

        Type(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }
}
