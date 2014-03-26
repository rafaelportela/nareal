package br.com.rafaelportela.nareal.repository;

import br.com.rafaelportela.nareal.model.Subject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.net.UnknownHostException;

public class Repository {

    private static MongoClient mongoClient;
    private static Jongo jongo;

    public static void setupEnvironment(String mongoUri) throws UnknownHostException {
        MongoClientURI mongoClientURII = new MongoClientURI(mongoUri);
        Repository.mongoClient = new MongoClient(mongoClientURII);
        Repository.jongo = new Jongo(mongoClient.getDB(mongoClientURII.getDatabase()));
    }

    public static Jongo getJongo() {
        return jongo;
    }

    public static Repository instance() {
        return new Repository();
    }

    private Repository() {}

    public Iterable<Subject> allSubjects() {
        MongoCollection subjects = jongo.getCollection("subjects");
        return subjects.find().as(Subject.class);
    }

    public void save(Subject subject) {
        jongo.getCollection("subjects").save(subject);
    }

    public Subject getBy(String id) {
        String query = String.format("{ _id: '%s'}", id);
        return jongo.getCollection("subjects").findOne(query).as(Subject.class);
    }
}
