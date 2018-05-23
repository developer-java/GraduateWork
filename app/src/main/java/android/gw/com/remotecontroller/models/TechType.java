package android.gw.com.remotecontroller.models;

import android.gw.com.remotecontroller.R;

public enum TechType {
    TV(R.drawable.tv,R.string.tv,R.string.tvDesc),
    COOL(R.drawable.conder,R.string.cool,R.string.coolDesc),
    COFFE(R.drawable.coffe,R.string.coffe,R.string.coffeDesc);
    private int imgId,strTitleId,descId;
    private TechType(int imgId,int strTitleId,int descId){
        this.imgId = imgId;
        this.descId = descId;
        this.strTitleId = strTitleId;
    }

    public int getDescId() {
        return descId;
    }

    public void setDescId(int descId) {
        this.descId = descId;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getStrTitleId() {
        return strTitleId;
    }

    public void setStrTitleId(int strTitleId) {
        this.strTitleId = strTitleId;
    }
}
