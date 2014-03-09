package br.com.rafaelportela.nareal.services;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class FeedlistServiceTest {

    @Test
    public void shouldReturnAnActivityList() throws Exception {

        assertThat(new FeedlistService().list(), is(notNullValue()));
    }
}
