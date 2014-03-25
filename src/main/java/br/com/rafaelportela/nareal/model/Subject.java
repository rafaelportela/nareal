package br.com.rafaelportela.nareal.model;

public class Subject {

    private String _id;
    private String title;

    public Subject() {}

    public Subject(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }
}
