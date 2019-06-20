package ru.job4j.socket;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 20/06/2019
 */

public class BotClientTest {
    private  static final String LN = System.lineSeparator();

    private void testServer(String input, String expected) throws IOException {
        InputStream stdIn = System.in;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(new byte[]{0, 0});

        when(socket.getOutputStream()).thenReturn(out);
        when(socket.getInputStream()).thenReturn(in);

        BotClient bot = new BotClient(socket);
        bot.init();
        assertThat(out.toString(), is(expected));
        System.setIn(stdIn);
    }

    @Test
    public void whenQuitThenShowAnswer() throws IOException {
        this.testServer("exit", String.format("exit%s", LN));
    }

}