package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalyzeTest {
    /**
     * Test unavailable.
     */
    @Test
    public void whenInputFileThenOutputUnavailableTime() throws Exception {
        Analyze testAnalyze = new Analyze();
        testAnalyze.unavailable("./../server.txt", "./../unavailable.csv");
        BufferedReader br = new BufferedReader(new FileReader("./../unavailable.csv"));
        assertThat(br.readLine(), is("10:57:01;10:59:01"));
        assertThat(br.readLine(), is("11:01:02;11:02:02"));
    }
}