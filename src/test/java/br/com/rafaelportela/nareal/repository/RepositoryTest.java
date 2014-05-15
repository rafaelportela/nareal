package br.com.rafaelportela.nareal.repository;

import br.com.rafaelportela.nareal.model.Subject;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.junit.*;

import java.net.UnknownHostException;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RepositoryTest {

    private Repository repository;

    @BeforeClass
    public static void setUp() throws Exception {
        String mongoUri = "mongodb://localhost:27017/nareal-test";
        Repository.setupEnvironment(mongoUri);
    }

    @Before
    public void clearData() throws UnknownHostException {
        Repository.getJongo()
                .getCollection("subjects").drop();

        repository = Repository.instance();
    }

    @Test
    public void insertSubject() {
        repository.save(new Subject("Java"));
        Iterable<Subject> subjects = repository.allSubjects();

        assertThat(newArrayList(subjects).size(), is(1));
    }

    @Test
    public void itGivesAnIDToPersistedSubject() {
        Subject createdSubject = new Subject("Java");
        repository.save(createdSubject);

        assertThat(createdSubject.getId(), is(notNullValue()));
    }

    @Test
    public void shouldGiveAnIdToPersistedObject() {
        Subject subject = new Subject("noSQL databases");
        repository.save(subject);
        assertThat(subject.getId(), is(notNullValue()));
    }

    @Test
    public void shouldReturnASubjectByGivenId() {
        Subject subject = new Subject("testing");
        repository.save(subject);

        Subject returnedSubject = repository.getBy(subject.getId());
        assertThat(returnedSubject.getTitle(), is("testing"));
    }
}