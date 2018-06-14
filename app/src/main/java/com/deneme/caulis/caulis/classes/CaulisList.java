package com.deneme.caulis.caulis.classes;

import java.util.ArrayList;

public class CaulisList {
    public interface CaulisMessageListener{
        public void newObjectArrived(CaulisMessage c);
    }

    private ArrayList<CaulisClasses> list;
    private CaulisMessageListener messageListener;

    public CaulisList(){
        this.list = new ArrayList<>();
        this.messageListener = null;
    }

    public void setMessageListener(CaulisMessageListener listener){
        this.messageListener = listener;
    }

    //Message add
    public void madd(CaulisMessage object){
        list.add(object);
        if (this.messageListener != null){
            messageListener.newObjectArrived(object);
        }
    }


}
