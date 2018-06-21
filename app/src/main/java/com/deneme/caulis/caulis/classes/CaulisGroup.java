
/***************************************************************************************************
 * A container for groups
 **************************************************************************************************/


package com.deneme.caulis.caulis.classes;

import org.json.JSONException;
import org.json.JSONObject;

public class CaulisGroup implements CaulisClasses {
    private String name;
    private String description;
    private String groupID;
    private String authorID;
    private int numberOfPeopleAllowed;
    /*private arraylist<> users;*/

    public CaulisGroup(String name, String description){
        this.name = name;
        this.description = description;
    }

    public CaulisGroup(JSONObject o) throws JSONException {
        this.name = o.get("name").toString();
        this.description = o.get("description").toString();
        this.groupID = o.get("groupID").toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public int getNumberOfPeopleAllowed() {
        return numberOfPeopleAllowed;
    }

    public void setNumberOfPeopleAllowed(int numberOfPeopleAllowed) {
        this.numberOfPeopleAllowed = numberOfPeopleAllowed;
    }
}
