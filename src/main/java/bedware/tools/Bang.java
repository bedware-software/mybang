package bedware.tools;

import org.snakeyaml.engine.v2.api.Load;
import org.snakeyaml.engine.v2.api.LoadSettings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

record Bang(String name, String shortcut, String uri) {
    static Map<String, Bang> bangs = new HashMap<>();
    static {
        Load load = new Load(LoadSettings.builder().build());
        try(InputStream inputStream = getConfig()) {
            ArrayList<Map<String, String>> bangsFromFile = (ArrayList<Map<String, String>>) load.loadFromInputStream(inputStream);

            for (Map<String,String> bang : bangsFromFile) {
                bangs.put(bang.get("shortcut"), new Bang(bang.get("name"), bang.get("shortcut"), bang.get("uri")));
                System.out.println(bang.get("name"));
            }
            System.out.println("Bangs loaded.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static InputStream getConfig() {
        return Optional
                // When in development
                .ofNullable(Bang.class.getClassLoader().getResourceAsStream("config.yaml"))
                // When it's in .jar
                .orElseGet(() -> {
                    try {
                        return new FileInputStream("config.yaml");
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                });
    }

    static final String DEFAULT_SEARCH_ENGINE = "https://duckduckgo.com/?q=%s";

    final static String BANG_MARKER = "!";

    public String getShortcutWithBangMarker() {
        return BANG_MARKER + shortcut;
    }
    static Optional<Bang> selectEngineByQuery(String searchQuery) {
        if (searchQuery.startsWith(BANG_MARKER)) {
            String bangFromQuery = getBangFromQuery(searchQuery);
            if (Bang.bangs.containsKey(bangFromQuery)) {
                return Optional.of(Bang.bangs.get(bangFromQuery));
            }
        }
        return Optional.empty();
    }

    private static String getBangFromQuery(String searchQuery) {
        return searchQuery.contains(" ") ? searchQuery.substring(BANG_MARKER.length(), searchQuery.indexOf(" ")) : searchQuery.substring(BANG_MARKER.length());
    }
}
