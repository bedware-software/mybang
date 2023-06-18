package bedware.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyBangServerTest {
    @Test
    void testBangEngine() {
        assertEquals("https://github.com/bedware?q=fzf&tab=stars", MyBangServer.BangEngine.bang("!ghs fzf"));
        assertEquals("https://github.com/bedware/.dotfiles", MyBangServer.BangEngine.bang("!ghme .dotfiles"));
        assertEquals("https://github.com/bedware/", MyBangServer.BangEngine.bang("!ghme"));
    }
}