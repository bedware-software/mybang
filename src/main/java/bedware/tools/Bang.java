package bedware.tools;

import org.snakeyaml.engine.v2.api.Load;
import org.snakeyaml.engine.v2.api.LoadSettings;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

record Bang(String name, String shortcut, String uri) {
    static Map<String, Bang> bangs = new HashMap<>();
    static {
        Load load = new Load(LoadSettings.builder().build());
        InputStream inputStream = Bang.class.getClassLoader().getResourceAsStream("config.yaml");
        ArrayList<Map<String, String>> bangsFromFile = (ArrayList<Map<String, String>>) load.loadFromInputStream(inputStream);
        for (Map<String,String> bang : bangsFromFile) {
            bangs.put(bang.get("shortcut"), new Bang(bang.get("name"), bang.get("shortcut"), bang.get("uri")));
        }
    }

    static final Bang DEFAULT_SEARCH_ENGINE = bangs.getOrDefault("d", new Bang("DuckDuckGo", "d", "https://duckduckgo.com/?q=%s"));

    final static String BANG_MARKER = "!";

    public String getShortcutWithBangMarker() {
        return BANG_MARKER + shortcut;
    }
    static Bang selectEngineByQuery(String searchQuery) {
        if (searchQuery.startsWith(BANG_MARKER)) {
            String bangFromQuery = getBangFromQuery(searchQuery);
            if (Bang.bangs.containsKey(bangFromQuery)) {
                return Bang.bangs.get(bangFromQuery);
            }
        }
        return Bang.DEFAULT_SEARCH_ENGINE;
    }

    private static String getBangFromQuery(String searchQuery) {
        return searchQuery.contains(" ") ? searchQuery.substring(BANG_MARKER.length(), searchQuery.indexOf(" ")) : searchQuery.substring(BANG_MARKER.length());
    }
}
