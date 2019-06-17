package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 17/06/2019
 */

public class SearchTest {
    /**
     * Test files.
     */
    @Test
    public void whenInputDirAndExtsThenShowListOfFiles() throws Exception {
        String tmpdir = System.getProperty("java.io.tmpdir");
        String prefix = "file";
        String suffix1 = ".txt";
        String suffix2 = ".doc";
        String suffix3 = ".xml";
        File root = new File(tmpdir, "rootDir");
        root.mkdir();
        File dir1 = new File(root, "dir1");
        dir1.mkdir();
        File dir2 = new File(root, "dir2");
        dir2.mkdir();
        File dir3 = new File(dir1, "dir3");
        dir3.mkdir();
        File tempFile = File.createTempFile(prefix, suffix1, root);
        File tempFile2 = File.createTempFile(prefix, suffix2, root);
        File tempFile3 = File.createTempFile(prefix, suffix3, root);
        File tempFile4 = File.createTempFile(prefix, suffix1, dir1);
        File tempFile5 = File.createTempFile(prefix, suffix2, dir1);
        File tempFile6 = File.createTempFile(prefix, suffix3, dir2);
        File tempFile7 = File.createTempFile(prefix, suffix1, dir2);
        File tempFile8 = File.createTempFile(prefix, suffix2, dir3);
        File tempFile9 = File.createTempFile(prefix, suffix3, dir3);

        List<String> exts = Arrays.asList("txt", "doc");
        Search testSearch = new Search();

        List<File> result = testSearch.files(root.getAbsolutePath(), exts, true);
        List<File> expected = Arrays.asList(tempFile, tempFile2, tempFile4, tempFile5, tempFile7, tempFile8);

        List<File> result2 = testSearch.files(root.getAbsolutePath(), exts, false);
        List<File> expected2 = Arrays.asList(tempFile3, tempFile6, tempFile9);

        assertThat(result.containsAll(expected), is(true));
        assertThat(result2.containsAll(expected2), is(true));
    }
}