package br.com.rafaelportela.nareal.repository;

import br.com.rafaelportela.nareal.model.Subject;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

public class Repository {

    private final Jongo jongo;

    public Repository(Jongo jongo) {
        this.jongo = jongo;
    }

    public Iterable<Subject> allSubjects() {
        MongoCollection subjects = jongo.getCollection("subjects");
        return subjects.find().as(Subject.class);
    }

    public void save(Subject subject) {
        jongo.getCollection("subjects").save(subject);
    }
}
