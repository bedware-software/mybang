package bedware.tools;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static bedware.tools.MyBangServer.BangEngine.bang;

public class MyBangServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", (exchange) -> {
            System.out.println("--------------");
            String searchQuery = dropBeginningSlash(exchange);
            String redirectTo = bang(searchQuery);
            exchange.getResponseHeaders().add("Location", redirectTo);
            exchange.sendResponseHeaders(307, 0);
        });
        server.setExecutor(null); // default executor
        server.start();
        System.out.println("Server started");
    }

    private static String dropBeginningSlash(HttpExchange exchange) {
        return exchange.getRequestURI().getPath().substring(1);
    }

    static class BangEngine {
        public static String bang(String query) {
            System.out.println("From:" + query);

            Bang selectedEngine = selectEngineByQuery(query);
            String queryWithoutBang = query.replace(selectedEngine.getShortcut(), "").trim();
            String target = selectedEngine.getUri().replace("%s", URLEncoder.encode(queryWithoutBang, StandardCharsets.UTF_8));

            System.out.println("To:" + target);

            return target;
        }

        private static Bang selectEngineByQuery(String searchQuery) {
            for (Bang engine : Bang.values()) {
                String shortcut = engine.getShortcut();
                String bangFromQuery = searchQuery.contains(" ") ? searchQuery.substring(0, searchQuery.indexOf(" ")) : searchQuery;
                if (bangFromQuery.equals(shortcut)) {
                    System.out.println("Bang found:" + shortcut);
                    return engine;
                }
            }
            System.out.println("Bang not found. Using default engine.");
            return Bang.DEFAULT_SEARCH_ENGINE;
        }

    }

    enum Bang {

        // Searching services
        DUCKDUCKGO("d", "https://duckduckgo.com/?q=%s"),
        GOOGLE("g", "https://google.com/search?q=%s"),
        STACKOVERFLOW("so", "https://duckduckgo.com/?q=%s site:stackoverflow.com"),
        NEOVIMCRAFT("vp", "https://neovimcraft.com/?search=%s"),
        MY_GITHUB_STARS("ghs", "https://github.com/bedware?q=%s&tab=stars"),
        MY_GITHUB_ME("ghme", "https://github.com/bedware/%s"),

        // Apps
        INFOMATE("news", "https://infomate.club/"),
        POWERSHELL_CHEATSHEET("pwsh", "https://github.com/PowerShell/PowerShell/tree/master/docs/learning-powershell#map-book-for-experienced-bash-users"),
        AI_LIBRARY("ailib", "https://library.phygital.plus/"),
        EXCALIDRAW("draw", "https://excalidraw.com/"),
        BING("bing", "https://www.bing.com/search?q=Bing+AI&showconv=1"),
        DEEL("deel", "https://app.deel.com"),
        GOOGLE_SHEETS("sheets", "https://docs.google.com/spreadsheets/"),
        GOOGLE_DOCUMENTS("docs", "https://docs.google.com/document/");

        static final Bang DEFAULT_SEARCH_ENGINE = Bang.DUCKDUCKGO;

        Bang(String shortcut, String uri) {
            this.shortcut = shortcut;
            this.uri = uri;
        }

        final private String shortcut;
        final private String uri;
        final static String BANG_MARKER = "!";

        public String getShortcut() {
            return BANG_MARKER + shortcut;
        }

        public String getUri() {
            return uri;
        }
    }
}
