package bedware.tools;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static bedware.tools.MyBangServer.BangEngine.bang;

public class MyBangServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", (exchange) -> {
            System.out.println("--------------");
            String searchQuery = exchange.getRequestURI().getPath().substring("/".length());
            String redirectTo = bang(searchQuery);
            exchange.getResponseHeaders().add("Location", redirectTo);
            exchange.sendResponseHeaders(307, 0);
            exchange.close();
        });
        server.start();
        System.out.println("Server started");
    }

    static class BangEngine {
        public static String bang(String query) {
            System.out.println("From:" + query);

            Optional<Bang> selectedEngine = Bang.selectEngineByQuery(query);
            String target;
            if (selectedEngine.isPresent()) {
                var engine = selectedEngine.get();
                String queryWithoutBang = query.replace(engine.getShortcutWithBangMarker(), "").trim();
                target = engine.uri().replace("%s", URLEncoder.encode(queryWithoutBang, StandardCharsets.UTF_8));
            } else {
                target = Bang.DEFAULT_SEARCH_ENGINE.replace("%s", query);
            }

            System.out.println("To:" + target);
            return target;
        }
    }
}
