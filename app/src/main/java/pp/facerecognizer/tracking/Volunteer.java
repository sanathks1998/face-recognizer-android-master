package pp.facerecognizer.tracking;


class Volunteer {
    private String location;
    private String service;
    private String numberreq;
    private String numreg;

    public Volunteer() {

    }

    public Volunteer(String location, String service, String numberreq, String numreg) {
        this.location = location;
        this.service = service;
        this.numberreq = numberreq;
        this.numreg = numreg;
    }

    public String getLocation() {
        return location;
    }

    public String getService() {
        return service;
    }

    public String getNumberreq() {
        return numberreq;
    }

    public String getNumreg() {
        return numreg;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setNumberreq(String numberreq) {
        this.numberreq = numberreq;
    }

    public void setNumreg(String numreg) {
        this.numreg = numreg;
    }
}
