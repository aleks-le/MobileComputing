package com.deneme.caulis.caulis.classes;

import java.util.ArrayList;
import java.util.HashMap;

public class CaulisList {
    public interface CaulisMessageListener{
        public void newObjectArrived(CaulisMessage c);
    }
    public interface CaulisGroupListener{
        public void newObjectArrived(CaulisGroup c);
    }

    private ArrayList<CaulisClasses> list;
    private CaulisMessageListener messageListener;
    private CaulisGroupListener groupListener;
    private HashMap<String, CaulisClasses> map;

    public CaulisList(){
        this.map = new HashMap<String, CaulisClasses>();
        this.list = new ArrayList<>();
        this.messageListener = null;
        this.groupListener = null;
    }

    public void setMessageListener(CaulisMessageListener listener){
        this.messageListener = listener;
    }

    public void setGroupListener(CaulisGroupListener listener){
        this.groupListener = listener;
    }

    //Message add
    public void madd(CaulisMessage object){
        if (map.containsKey(object.getMessageID())==false) {
            map.put(object.getMessageID(),object);
            list.add(object);
            if (this.messageListener != null) {
                messageListener.newObjectArrived(object);
            }
        }
    }

    public void gadd(CaulisGroup object){
        if (map.containsKey(object.getGroupID())==false) {
            map.put(object.getGroupID(),object);
            list.add(object);
            if (this.groupListener != null) {
                groupListener.newObjectArrived(object);
            }
        }
    }

    public CaulisGroup getLastGroup(){
        if (list.size()!=0) {
            return (CaulisGroup) list.get(list.size() - 1);
        }else {
            return null;
        }
    }




}
