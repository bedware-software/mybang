package bedware.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BangTest {
    @Test
    void testMultipleBangsLoaded() {
        assertTrue(Bang.bangs.size() > 0);
    }

    @Test
    void testSelectEngineByQuery() {
        Bang bang = Bang.selectEngineByQuery("!ghs fzf");
        assertEquals("MY_GITHUB_STARS", bang.name());
        Bang bang2 = Bang.selectEngineByQuery("!ghme");
        assertEquals("MY_GITHUB_ME", bang2.name());
    }
}