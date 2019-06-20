package ru.job4j.socket;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

public class BotServerTest {
    private  static final String LN = System.lineSeparator();

    private void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getOutputStream()).thenReturn(out);
        when(socket.getInputStream()).thenReturn(in);
        BotServer bot = new BotServer(socket);
        bot.init();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenQuitThenShowAnswer() throws IOException {
        this.testServer("exit", String.format("Bye! See you next time!%s%s", LN, LN));
    }

    @Test
    public void whenHelloThenShowAnswer() throws IOException {
        this.testServer(
                Joiner.on(LN).join(
                        "hello",
                        "exit"
                ), Joiner.on(LN).join(
                        "Hello, dear friend, I'm oracle.",
                        "",
                        "Bye! See you next time!",
                        "",
                        ""
                ));
    }

    @Test
    public void whenUnsupportedThenShowAnswer() throws IOException {
        this.testServer(
                Joiner.on(LN).join(
                        "What's up?",
                        "exit"
                ), Joiner.on(LN).join(
                        "It's too difficult question",
                        "",
                        "Bye! See you next time!",
                        "",
                        ""
                ));
    }

}