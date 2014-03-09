package br.com.rafaelportela.nareal.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Activity {
    public String content;
    public String title;

    public Activity() {}

    public Activity(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
