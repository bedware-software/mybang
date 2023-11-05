package bedware.tools;

import static bedware.tools.MyBangServer.BangEngine.bang;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MyBangServerTest {

    @Test
    void testBangEngine() {
        assertEquals("https://github.com/bedware?q=fzf&tab=stars", bang("!ghs fzf"));
        assertEquals("https://github.com/bedware/.dotfiles", bang("!ghme .dotfiles"));
        assertEquals("https://github.com/bedware/", bang("!ghme"));
        assertEquals("https://duckduckgo.com/?q=%21dh", bang("!dh"));
        assertEquals("https://neovim.io/doc/user/", bang("!nvim"));
    }

    @Test
    void decodingTest() {
        assertEquals("https://docs.julialang.org/en/v1/search/?q=test+welcome", bang("!julia test welcome"));
        // Chrome
        assertEquals("https://docs.julialang.org/en/v1/search/?q=chaika", bang("!julia%20chaika"));
        // Firefox
        assertEquals("https://docs.julialang.org/en/v1/search/?q=self", bang("%21julia+self"));
    }
}
