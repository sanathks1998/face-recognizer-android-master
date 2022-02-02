package pp.facerecognizer.tracking;

public class Camp {
    private String location;
    private String contact;
    private String children;
    private String men;
    private String women;

    public Camp() {
    }

    public Camp(String location, String contact, String children, String men, String women) {
        this.location = location;
        this.contact = contact;
        this.children=children;
        this.men = men;
        this.women = women;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMen() {
        return men;
    }

    public void setMen(String men) {
        this.men = men;
    }

    public String getWomen() {
        return women;
    }

    public void setWomen(String women) {
        this.women = women;
    }
}
