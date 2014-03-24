package br.com.rafaelportela.nareal.repository;

import br.com.rafaelportela.nareal.model.Subject;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.junit.*;

import java.net.UnknownHostException;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RepositoryTest {

    private static MongoClient mongoClient;
    private static Jongo jongo;

    private Repository repository;

    @BeforeClass
    public static void setUp() throws Exception {
        mongoClient = new MongoClient("localhost", 27017);
        jongo = new Jongo(mongoClient.getDB("nareal-test"));
    }

    @Before
    public void clearData() throws UnknownHostException {
        jongo.getCollection("subjects").drop();
        repository = new Repository(jongo);
    }

    @AfterClass
    public static void tearDown() {
        mongoClient.close();
    }

    @Test
    public void insertSubject() {
        repository.save(new Subject("Java"));
        Iterable<Subject> subjects = repository.allSubjects();

        assertThat(newArrayList(subjects).size(), is(1));
    }
}